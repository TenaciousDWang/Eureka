package com.wang.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class RibbonEurekaApplication {
	
    // 这里直接使用RestTemplate发送get请求
    @Autowired
    RestTemplate restTemplate;
    @Bean
    @LoadBalanced // 开启负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(RibbonEurekaApplication.class, args);
	}
	
    @RequestMapping("/haha")
    public String haha(@RequestParam(value = "name", defaultValue = "wang") String name) {
    	return restTemplate.getForObject("http://eureka-client-provider/hello?name="+name, String.class);
    }

}
