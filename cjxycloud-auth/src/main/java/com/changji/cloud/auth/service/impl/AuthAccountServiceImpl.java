package com.changji.cloud.auth.service.impl;

import com.changji.cloud.api.leaf.enums.LeafKeyEnum;
import com.changji.cloud.api.leaf.feign.SegmentFeignClient;
import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.auth.dto.AuthenticationDTO;
import com.changji.cloud.auth.mapper.AuthAccountMapper;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.core.utils.IpUtils;
import com.changji.cloud.common.core.utils.ServletUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:33 2022/8/20
 * @ Modified By：
 */
@Service
public class AuthAccountServiceImpl implements AuthAccountService {

    @Autowired
    private WebsiteFeignClient websiteFeignClient;

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private SegmentFeignClient segmentFeignClient;

    @Override
    @Transactional
    public AuthAccount login(String username, String password) {


        //从数据库查询用户
        AuthAccount authAccount = authAccountMapper.queryByUserName(username);

        if (authAccount != null) {
            return authAccount;
        }

        //从教务管理系统查询用户
        ServerResponseEntity<AuthAccountVO> byUserNameAndPassword = websiteFeignClient.getByUserNameAndPassword(username, password);
        if (!byUserNameAndPassword.isSuccess()) {
            throw new ServiceException(byUserNameAndPassword.getMsg());
        }
        AuthAccountVO authAccountVO = byUserNameAndPassword.getData();
        authAccount  = mapperFacade.map(authAccountVO, AuthAccount.class);
        authAccount.setStatus(1);

        // 从leaf获取uid
        ServerResponseEntity<Long> uidResponseEntity = segmentFeignClient.getSegmentId(LeafKeyEnum.AUTH_UID_KEY.value());
        ServerResponseEntity<Long> userIdResponseEntity = segmentFeignClient.getSegmentId(LeafKeyEnum.USER_ID_KEY.value());
        if (!uidResponseEntity.isSuccess() || !userIdResponseEntity.isSuccess()) {
            throw new ServiceException("leaf获取id失败");
        }
        authAccount.setUid(uidResponseEntity.getData());
        authAccount.setUserId(userIdResponseEntity.getData());
        authAccount.setCreateIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        authAccountMapper.save(authAccount);

        return authAccount;
    }
}
