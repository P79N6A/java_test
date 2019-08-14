package com.tre.jdevtemplateboot.common.redis;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 12:52
 **/
public interface IRedisService {

    /**
    * @Description:  对key增加前缀，可用于分类，避免key重复
    * @Param: [prefix, key]
    * @return:
    * @Author: JDev
    * @Date: 2019/03/27
    **/
    String get(IKeyPrefix prefix,String key);
}
