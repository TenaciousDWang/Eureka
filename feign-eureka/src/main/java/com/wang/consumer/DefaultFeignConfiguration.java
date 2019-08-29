package com.wang.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.Retryer;

@Configuration
public class DefaultFeignConfiguration {
    /**
     * 建立socket超时时间 读取响应socket超时时间
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 5000);
    }

    /**
     * 自定义重试次数
     */
    @Bean
    public Retryer feignRetried(){
    	//1、请求重试的间隔算法参数 2、请求间隔最大时间 3、重试的次数 
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1L),5);
    }
    /**
     * 自定义日志级别
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }
}
