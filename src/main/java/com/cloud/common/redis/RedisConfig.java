package com.cloud.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liulijun
 * redis配置类
 *  此类的主要配置功能为:序列化、cacheManager、redisTemplate、键值生成策略
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.redis", name = "host")
public class RedisConfig {

    @Autowired
    private RedisProperties properties;

    @Autowired
    private KeyExpiredListener keyExpiredListener;

    @Bean
    @Scope("prototype")
    public RedisConnectionFactory connectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(properties.getPool().getMaxActive());
        poolConfig.setMaxIdle(properties.getPool().getMaxIdle());
        poolConfig.setMaxWaitMillis(properties.getPool().getMaxWait());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestWhileIdle(true);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(properties.getHost());
        if(null != properties.getPassword()){
            jedisConnectionFactory.setPassword(properties.getPassword());
        }
        jedisConnectionFactory.setPort(properties.getPort());
        return jedisConnectionFactory;
    }

    @Bean(name="redisMsgListenerContainer")
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer container = new  RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    /**
     * 不使用事务
     * @param connectionFactory
     * @return
     */
    @Bean(name="redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer()) ;
        template.setEnableTransactionSupport(false);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 使用事务
     * @param connectionFactory
     * @return
     */
    @Bean(name="transRedisTemplate")
    public RedisTemplate transRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(connectionFactory);
        //使用Pojo进行序列化操作,或者使用FastJsonRedisSerializer也行
        PojoSerializable pojoSerializable = new PojoSerializable(Object.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer()) ;
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisKeyspaceNotifier redisKeyspaceNotifier(RedisConnectionFactory connectionFactory) {
        RedisKeyspaceNotifier notifier = new RedisKeyspaceNotifier();
        notifier.setNeedListener(true);
        notifier.setListener(keyExpiredListener);
        notifier.setConnectionFactory((JedisConnectionFactory) connectionFactory);
        notifier.init();
        return notifier;
    }

}
