package com.changji.cloud.api.leaf.feign;


import com.changji.cloud.common.core.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 20:25 2022/7/24
 * @ Modified By：
 */
@FeignClient(value = "cjxycloud-leaf",contextId = "segment")
public interface SegmentFeignClient {

    /**
     * 获取id
     * @param key
     * @return
     */
    @GetMapping(value ="/feign/insider/segment")
    ServerResponseEntity<Long> getSegmentId(@RequestParam("key") String key);

}

