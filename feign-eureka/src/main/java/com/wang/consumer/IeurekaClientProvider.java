package com.wang.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * eureka-client-provider之前启动的服务名
 */
@FeignClient(value = "eureka-client-provider")
public interface IeurekaClientProvider {
	
    @GetMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);

}
