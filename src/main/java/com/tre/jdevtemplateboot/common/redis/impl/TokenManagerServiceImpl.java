package com.tre.jdevtemplateboot.common.redis.impl;

import com.tre.jdevtemplateboot.common.pojo.TokenModelBean;
import com.tre.jdevtemplateboot.common.pojo.UserPrefix;
import com.tre.jdevtemplateboot.common.redis.IRedisService;
import com.tre.jdevtemplateboot.common.redis.ITokenManagerService;
import com.tre.jdevtemplateboot.common.util.LLoggerUtils;
import com.tre.jdevtemplateboot.exception.SystemExceptionToken;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 10:05
 **/
@Service
public class TokenManagerServiceImpl implements ITokenManagerService {

    private static Logger logBusiness = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IRedisService redisService;

    private static String preFixUserId;

    @Override
    public TokenModelBean createToken(String userId) throws SystemExceptionToken {

        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModelBean model = new TokenModelBean(userId, token);

        //对key增加前缀，可用于分类，避免key重复
        preFixUserId = getPrefixUserId(userId);
        redisTemplate.opsForValue().set(preFixUserId, token, getExpireHours(), TimeUnit.HOURS);
        logBusiness.info("token管理->创建一个token:{}->{}",preFixUserId,token);
        return model;
    }

    @Override
    public boolean checkToken(TokenModelBean model) throws SystemExceptionToken {
        if (model == null) {
            return false;
        }

        //对key增加前缀，可用于分类，避免key重复
        preFixUserId = getPrefixUserId(model.getUserId());
        String token = redisTemplate.opsForValue().get(preFixUserId).toString();
        if(token == null || !token.equals(model.getToken())){
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.expire(preFixUserId, getExpireHours(), TimeUnit.HOURS);
        logBusiness.info("token管理->检查token是否有效,有效的话，并延长token的过期时间:{}->{}",preFixUserId,token);
        return true;
    }

    @Override
    public TokenModelBean getToken(String authentication) throws SystemExceptionToken {

        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }

        //使用userId和源token简单拼接成的token，可以增加加密措施
        String token = param[0];
        String userId = param[1];
        logBusiness.info("token管理->从字符串中解析token:{}->{}",userId,token);

        return new TokenModelBean(userId,token);
    }

    @Override
    public void deleteToken(String userId) throws SystemExceptionToken {
        preFixUserId = getPrefixUserId(userId);
        redisTemplate.delete(preFixUserId);
        logBusiness.info("token管理->清除token key:{}",preFixUserId);
    }


    /**
    * @Description: 对key增加前缀，可用于分类，避免key重复
    * @Param: [userId]
    * @return: java.lang.String
    * @Author: JDev
    * @Date: 2019/03/27
    **/
    private String getPrefixUserId(String userId){
        return redisService.get(UserPrefix.getUserPrefix, userId);
    }

    /**
    * @Description:  获取存储期限
    * @Param: []
    * @return: int
    * @Author: JDev
    * @Date: 2019/03/27
    **/
    private int getExpireHours(){
        return  UserPrefix.getUserPrefix.getExpireHours();
    }
}
