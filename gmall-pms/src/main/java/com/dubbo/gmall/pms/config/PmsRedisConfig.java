package com.dubbo.gmall.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

/**
 * 将自己创建出自己满意的序列化器放入容器中
 * @Author ZengHao
 * @Date 2020/1/14 16:13
 */
@Configuration
public class PmsRedisConfig {

    /**
     * GenericJackson2JsonRedisSerializer:支持泛型的json序列化器(存放的对象里面可以有泛型的数据)
     */
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
