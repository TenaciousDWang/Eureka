package com.wang.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
@SpringBootApplication
@RestController
@EnableEurekaClient
public class FeignEurekaApplication {
	
	@Autowired
    private IeurekaClientProvider eurekaClientProvider;

	public static void main(String[] args) {
		SpringApplication.run(FeignEurekaApplication.class, args);
	}
	
    @GetMapping("/haha")
    public String haha(@RequestParam(value = "name") String name) {
        return eurekaClientProvider.hello(name);
    }

}
