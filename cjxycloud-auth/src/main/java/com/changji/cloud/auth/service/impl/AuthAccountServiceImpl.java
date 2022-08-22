package com.changji.cloud.auth.service.impl;

import com.changji.cloud.api.leaf.enums.LeafKeyEnum;
import com.changji.cloud.api.leaf.feign.SegmentFeignClient;
import com.changji.cloud.api.user.feign.UserFeignClient;
import com.changji.cloud.api.user.feign.dto.UserDTO;
import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.auth.mapper.AuthAccountMapper;
import com.changji.cloud.auth.model.AuthAccount;
import com.changji.cloud.auth.service.AuthAccountService;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.common.core.utils.IpUtils;
import com.changji.cloud.common.core.utils.ServletUtils;
import com.changji.cloud.common.security.enums.AuthRoleEnum;
import com.changji.cloud.common.security.model.LoginUser;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private UserFeignClient userFeignClient;

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private SegmentFeignClient segmentFeignClient;

    @Override
    @Transactional
    public LoginUser login(String account, String password) {


        //从数据库查询用户
        AuthAccount authAccount = authAccountMapper.queryByAccount(account);

        if (authAccount != null) {
            //查询用户权限列表
            List<String> permission = authAccountMapper.queryUserMenuByUid(authAccount.getUid());
            return toLoginUser(authAccount, permission);
        }

        //从教务管理系统查询用户 远程调用website模块
        ServerResponseEntity<AuthAccountVO> byUserNameAndPassword = websiteFeignClient.getByAccountAndPassword(account, password);
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
        Long userId = userIdResponseEntity.getData();
        authAccount.setUid(uidResponseEntity.getData());
        authAccount.setUserId(userId);
        authAccount.setCreateIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        //保存用户到auth数据库
        authAccountMapper.save(authAccount);
        //保存用户角色到数据库
        authAccountMapper.saveAuthRole(uidResponseEntity.getData(), AuthRoleEnum.COMMON_ROLE.value());

        //同步到user模块
        UserDTO userDTO = mapperFacade.map(authAccountVO, UserDTO.class);
        userDTO.setUserId(userId);
        ServerResponseEntity<Object> userResponseEntity = userFeignClient.saveUserInfo(userDTO);
        if (!userResponseEntity.isSuccess()) {
            throw new ServiceException("同步用户信息失败");
        }

        //获取角色权限
        List<String> permission = authAccountMapper.queryUserMenuByUid(authAccount.getUid());
        return toLoginUser(authAccount,permission);
    }

    @Override
    public LoginUser refresh(LoginUser loginUser) {
        //从数据库查询用户
        AuthAccount authAccount = authAccountMapper.queryByAccount(loginUser.getAccount());
        //查询用户权限列表
        List<String> permission = authAccountMapper.queryUserMenuByUid(authAccount.getUid());
        LoginUser user = toLoginUser(authAccount, permission);
        //保持token一致
        user.setToken(loginUser.getToken());
        return user;
    }

    public LoginUser toLoginUser(AuthAccount authAccount,  List<String> permissions) {
        LoginUser loginUser = mapperFacade.map(authAccount, LoginUser.class);
        loginUser.setPermissions(permissions);
        loginUser.setUserId(authAccount.getUserId());
        return loginUser;
    }
}
