package com.changji.cloud.test;

import com.changji.cloud.common.security.aspect.PreAuthorizeAspect;
import com.changji.cloud.test.controller.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 0:52 2022/8/19
 * @ Modified By：
 */

@SpringBootTest
public class AspectTest {

    @Autowired
    private TestController testController;

    @Autowired
    PreAuthorizeAspect preAuthorizeAspect;

    @Test
    public void test01() {
        testController.test();
    }
}
