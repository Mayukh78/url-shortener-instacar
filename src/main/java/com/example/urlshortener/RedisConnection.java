package com.example.urlshortener;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisConnection {
    private String hostname="redis";
    private String port="6379";

    public StatefulRedisConnection<String,String> connection()
    {
        String connection_uri= "redis://" + hostname + ":" + port;
        RedisClient redisClient = RedisClient.create(connection_uri);
        StatefulRedisConnection<String,String> redisConnection = redisClient.connect();
        return redisConnection;
    }
}
