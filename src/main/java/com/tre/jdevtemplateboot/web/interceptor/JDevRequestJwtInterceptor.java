package com.tre.jdevtemplateboot.web.interceptor;

import com.tre.jdevtemplateboot.common.constant.SysConstantJwToken;
import com.tre.jdevtemplateboot.common.constant.SysConstantToken;
import com.tre.jdevtemplateboot.common.util.LLoggerUtils;
import com.tre.jdevtemplateboot.exception.SystemExceptionToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-28 09:22
 **/
@Component
public class JDevRequestJwtInterceptor  implements HandlerInterceptor {

    private Logger loggerBusiness = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从header中得到token
        String authHeader = request.getHeader(SysConstantToken.AUTHORIZATION);
        //规避探测性质的 OPTIONS请求
        String optionsString = "OPTIONS";
        if (optionsString.equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return  true;

        }else {
            //验证token
            if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(SysConstantJwToken.JWT_AUTHOR_TYPE)){
//                throw new SysServletJwtException(SysConstantInfo.SERVER_REQUEST_AUTHENTICATION_CODE
//                        ,SysConstantInfo.SERVER_REQUEST_AUTHENTICATION_MSG);
                return true;
            }
            String token = authHeader.substring(SysConstantJwToken.JWT_AUTHOR_TYPE.length());
            try {
                //使用jwt paser来验证签名
                Claims claims = Jwts.parser().setSigningKey(SysConstantJwToken.JWT_SECERT).parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);

            }catch (ExpiredJwtException e){
                throw new ServletException(new SystemExceptionToken("token expired!"));
            }catch (SignatureException e){
                throw new ServletException(new SystemExceptionToken("token invalid!"));
            }catch (Exception e){
                throw new ServletException(new SystemExceptionToken("token 验证失败!"));
            }finally {
                return  true;
            }

        }
       // return  true;
    }
}
