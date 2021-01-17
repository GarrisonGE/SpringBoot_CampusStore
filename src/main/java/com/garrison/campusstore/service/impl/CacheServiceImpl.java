package com.garrison.campusstore.service.impl;

import com.garrison.campusstore.cache.JedisUtil;
import com.garrison.campusstore.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    private JedisUtil.Keys jedisKey;

    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keySet = jedisKey.keys(keyPrefix + "*");
        for(String key: keySet){
            jedisKey.del(key);
        }
    }
}
