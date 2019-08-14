package com.tre.jdevtemplateboot.web.interceptor;

import com.tre.jdevtemplateboot.annotatiaon.Authentication;
import com.tre.jdevtemplateboot.common.constant.SysConstantInfo;
import com.tre.jdevtemplateboot.common.constant.SysConstantToken;
import com.tre.jdevtemplateboot.common.enums.ResultEnum;
import com.tre.jdevtemplateboot.common.pojo.TokenModelBean;
import com.tre.jdevtemplateboot.common.redis.ITokenManagerService;
import com.tre.jdevtemplateboot.common.util.LLoggerUtils;
import com.tre.jdevtemplateboot.exception.SystemExceptionToken;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: 请求服务资源的认证拦截
 * @author: JDev
 * @create: 2018-11-14 11:22
 **/
@Component
public class JDevRequestInterceptor implements HandlerInterceptor {

    private Logger loggerBusiness = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    @Autowired
    private ITokenManagerService tokenManagerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), ResultEnum.LOGIN_FAIL_AUTHENTICATION.getMsg());
            loggerBusiness.info("请求服务资源的认证：{}",ResultEnum.LOGIN_FAIL_AUTHENTICATION.getMsg());
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //从header中得到token
        String authHeader = request.getHeader(SysConstantToken.AUTHORIZATION);
        //验证token
        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(SysConstantToken.AUTHOR_TYPE)){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), ResultEnum.LOGIN_FAIL_HAS_TOKEN.getMsg());
            loggerBusiness.info("请求服务资源的认证Token验证：{}",ResultEnum.LOGIN_FAIL_HAS_TOKEN.getMsg());
            throw new SystemExceptionToken(SysConstantInfo.SERVER_REQUEST_AUTHENTICATION_CODE,SysConstantInfo.SERVER_REQUEST_AUTHENTICATION_MSG);
        }else{
            String token = authHeader.substring(SysConstantToken.AUTHOR_TYPE.length());
            TokenModelBean modelBean = tokenManagerService.getToken(token);
            if(tokenManagerService.checkToken(modelBean)){
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(SysConstantToken.CURRENT_USER_ID,modelBean.getUserId());
                return true;
            }
        }

        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authentication.class) != null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), ResultEnum.LOGIN_FAIL_VALIDATOR_TOKEN.getMsg());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            loggerBusiness.info("请求服务资源的认证Token验证：{}",ResultEnum.LOGIN_FAIL_VALIDATOR_TOKEN.getMsg());
            throw new SystemExceptionToken(SysConstantInfo.SERVER_REQUEST_AUTHENTICATION_CODE,SysConstantInfo.SERVER_REQUEST_AUTHENTICATION_MSG);
        }

        return true;
    }

}
