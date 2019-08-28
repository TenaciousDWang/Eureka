package com.wang.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class EurekaClientConsumerApplication {	
    // eureka客户端，可以获取到eureka中服务的信息
    @Autowired
    private DiscoveryClient discoveryClient;
    // 这里直接使用RestTemplate发送get请求
    @Autowired
    RestTemplate restTemplate;
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientConsumerApplication.class, args);
	}

    @RequestMapping("/haha")
    public String haha(@RequestParam(value = "name", defaultValue = "wang") String name) {
    	if(null != serviceUrl()) {
    		return restTemplate.getForObject(serviceUrl()+"/hello?name="+name, String.class);
    	}else {
    		return "接口调用失败，接口不存在";
    	}
    }
    public String serviceUrl(){ 
        List <ServiceInstance> list = discoveryClient.getInstances("eureka-client-provider"); 
        if(list != null && list.size() > 0){//我们只注册了一个服务提供者直接取第一个服务实例
        	String baseUrl = "http://" + list.get(0).getHost() + ":" + list.get(0).getPort();
            return baseUrl;             
        } 
        return null;        
    }
}

