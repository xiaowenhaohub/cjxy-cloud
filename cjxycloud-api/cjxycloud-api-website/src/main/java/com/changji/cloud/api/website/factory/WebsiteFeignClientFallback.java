package com.changji.cloud.api.website.factory;

import com.changji.cloud.api.website.feign.WebsiteFeignClient;
import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:10 2022/8/23
 * @ Modified By：
 */
@Component
@Slf4j
public class WebsiteFeignClientFallback implements FallbackFactory<WebsiteFeignClient> {


    @Override
    public WebsiteFeignClient create(Throwable throwable) {
        log.error("官网服务调用失败:{}", throwable.getMessage());

        return new WebsiteFeignClient() {
            @Override
            public ServerResponseEntity<AuthAccountVO> getByAccountAndPassword(String account, String password) {
                return ServerResponseEntity.showFailMsg("查询用户失败:" + throwable.getMessage());
            }

            @Override
            public ServerResponseEntity<Object> testFallback(String username) {
                return ServerResponseEntity.showFailMsg("测试失败:" + throwable.getMessage());
            }
        };
    }
}
