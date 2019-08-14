package com.tre.jdevtemplateboot.common.redis.impl;

import com.tre.jdevtemplateboot.common.redis.IKeyPrefix;
import com.tre.jdevtemplateboot.common.redis.IRedisService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 12:55
 **/
@Service
public class RedisServiceImpl  implements IRedisService {

    @Override
    public String get(IKeyPrefix prefix, String key) {

        //对key增加前缀，可用于分类，避免key重复
        String realKey=prefix.getPrefix() + key;
        return realKey;
    }
}
