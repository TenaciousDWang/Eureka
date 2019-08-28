package com.wang.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientProviderApplication {
	
    @Value("${server.port}")
    String port;

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientProviderApplication.class, args);
	}
	
    @RequestMapping("/hello")
    public String home(@RequestParam(value = "name", defaultValue = "wang") String name) {
        return "你好"+name+"这端口是"+port;
    }

}
