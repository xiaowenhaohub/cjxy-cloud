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
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public AuthAccount login(String username, String password) {

        AuthAccount authAccount = authAccountMapper.queryByUserName(username);

        if (authAccount != null) {
            return authAccount;
        }
        ServerResponseEntity<AuthAccountVO> byUserNameAndPassword = websiteFeignClient.getByUserNameAndPassword(username, password);

        if (!byUserNameAndPassword.isSuccess()) {
            throw new ServiceException(byUserNameAndPassword.getMsg());
        }
        AuthAccountVO authAccountVO = byUserNameAndPassword.getData();

        ServerResponseEntity<Long> responseEntity = segmentFeignClient.getSegmentId(LeafKeyEnum.AUTH_UID_KEY.value());

        if (!responseEntity.isSuccess()) {
            throw new ServiceException("获取uid 失败");
        }

        authAccount  = mapperFacade.map(authAccountVO, AuthAccount.class);
        authAccount.setUid(responseEntity.getData());
        authAccount.setStatus(1);

        System.out.println(authAccount);

        return null;
    }
}
