package com.tre.jdevtemplateboot.common.pojo;

import com.tre.jdevtemplateboot.common.redis.IKeyPrefix;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 11:32
 **/
public class BasePrefix  implements IKeyPrefix {

    private int expireHours;
    private String prefix;

    // 0表示不过期
    public BasePrefix(String prefix){
        this(0,prefix);
    }

    public BasePrefix(int expireHours,String prefix){
        this.expireHours = expireHours;
        this.prefix = prefix;
    }

    @Override
    public int getExpireHours() {
        return this.expireHours;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }
}
