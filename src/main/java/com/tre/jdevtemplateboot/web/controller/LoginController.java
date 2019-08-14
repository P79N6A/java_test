package com.tre.jdevtemplateboot.web.controller;

import com.tre.jdevtemplateboot.common.constant.SysConstantJwToken;
import com.tre.jdevtemplateboot.common.pojo.ResponseResult;
import com.tre.jdevtemplateboot.common.pojo.TokenModelBean;
import com.tre.jdevtemplateboot.common.redis.ITokenManagerService;
import com.tre.jdevtemplateboot.common.util.LJwtUtils;
import com.tre.jdevtemplateboot.common.util.LLoggerUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 登录API
 * @author: JDev
 * @create: 2018-12-07 16:17
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    private Logger logger = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    @Autowired
    private ITokenManagerService tokenManagerService;

    //登录API
    @PostMapping("/login")
    public ResponseResult loginJwt(@RequestParam(value="username") String userName,
                                   @RequestParam(value="password") String passWord){

        //假设验证过了用户名和密码 发token


        logger.info("UserName:{}",userName);
        String strToken = LJwtUtils.generateToken(userName);

        logger.info("Get token:{}",strToken);
        // Create Twt token
        return ResponseResult.buildOK(SysConstantJwToken.JWT_AUTHOR_TYPE+ strToken);
    }


    //登录API
    @PostMapping("/login/token")
    public ResponseResult login(@RequestParam(value="username") String userName,
                                @RequestParam(value="password") String passWord){

        //判断用户名是否存在
        /*User user = userServer.getUser(userName);
        if (user == null) {
            return ResponseResult.buildError(ResultEnum.LOGIN_FAIL);
        }*/

        //获取数据库中的密码，与输入的密码加密后比对
        /*String dbPassword =user.getPassWord();
        String equalPassword = LMD5Utils.inputPassToDbPass(password);
        if (!equalPassword.equals(dbPassword)) {
            return ResponseResult.buildError(ResultEnum.LOGIN_FAIL_PASS);
        }*/

        //验证过了用户名和密码 生成一个token，保存用户登录状态
        TokenModelBean modelBean = tokenManagerService.createToken(userName) ;


        logger.info("UserName:{}",userName);
        logger.info("Get token:{}",modelBean);

        // Create Twt token
        return ResponseResult.buildOK(modelBean);
    }

}
