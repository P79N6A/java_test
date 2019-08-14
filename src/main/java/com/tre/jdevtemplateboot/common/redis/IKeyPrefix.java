package com.tre.jdevtemplateboot.common.redis;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 11:30
 **/
public interface IKeyPrefix {

    /**
     * 有效期
     * @return
     */
    public int getExpireHours();

    /**
     * key前缀，防止其他的人使用redis时覆盖
     * @return
     */
    public String getPrefix();
}
