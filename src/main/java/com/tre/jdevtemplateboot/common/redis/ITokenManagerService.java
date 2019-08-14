package com.tre.jdevtemplateboot.common.redis;

import com.tre.jdevtemplateboot.common.pojo.TokenModelBean;
import com.tre.jdevtemplateboot.exception.SystemExceptionToken;

/**
 * @description: token管理
 * @author: JDev
 * @create: 2019-03-27 09:50
 **/
public interface ITokenManagerService {

    /**
    * @Description:  创建一个token关联上指定用户
    * @Param: [userId]
    * @return:
    * @Author: JDev
    * @Date: 2019/03/27
    **/
    TokenModelBean createToken(String userId) throws SystemExceptionToken;

    /**
    * @Description:  检查token是否有效
    * @Param: [model]
    * @return: boolean
    * @Author: JDev
    * @Date: 2019/03/27
    **/
    boolean checkToken(TokenModelBean model) throws SystemExceptionToken;

    /**
    * @Description: 从字符串中解析token
    * @Param: [authentication]  加密后的字符串
    * @return:
    * @Author: JDev
    * @Date: 2019/03/27
    **/
    TokenModelBean getToken(String authentication) throws SystemExceptionToken;

    /** 
    * @Description:  清除token
    * @Param: [userId] 
    * @return: void 
    * @Author: JDev
    * @Date: 2019/03/27 
    **/
    void deleteToken(String userId) throws SystemExceptionToken;

}
