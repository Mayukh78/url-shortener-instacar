package com.example.urlshortener;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.Calendar;

public class RateLimiter {
    private String ip;
//    Calendar cal= Calendar.getInstance();
    private Integer curr_min= Calendar.getInstance().MINUTE;
    private Integer limit=100; //this contains rate limit
    private long time= 60; //this contains TTL for key

    public RateLimiter(String ip) {
        this.ip = ip;
    }


    public boolean isOver_limit() {
        String key = ip + curr_min.toString();
        StatefulRedisConnection<String,String> connection= new RedisConnection().connection();
        RedisCommands<String,String> command = connection.sync();
        if(command.get(key) == null || Integer.parseInt(command.get(key))< limit-1)
        {
            if(command.get(key) == null)
            {
                //this transaction creates the key and set TTL 60.
                command.multi();
                command.incr(key);
                command.expire(key,time);
                command.exec();
            }
            else
                command.incr(key);
            //currently ip is under limit
            return false;
        }
        else
            return true;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCurr_min() {
        return curr_min;
    }

    public void setCurr_min(int curr_min) {
        this.curr_min = curr_min;
    }
}
