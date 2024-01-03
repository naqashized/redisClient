package com.redisson.redisClient.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redisson")
public class RedissonConfig{
    @Getter
    @Setter
    private RedisServer singleServerConfig;

    public static class RedisServer{
        @Getter
        @Setter
        private String address;
    }
}


