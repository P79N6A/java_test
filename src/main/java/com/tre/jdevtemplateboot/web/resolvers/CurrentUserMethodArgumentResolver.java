package com.tre.jdevtemplateboot.web.resolvers;

import com.tre.jdevtemplateboot.annotatiaon.CurrentUser;
import com.tre.jdevtemplateboot.common.constant.SysConstantInfo;
import com.tre.jdevtemplateboot.common.constant.SysConstantToken;
import com.tre.jdevtemplateboot.domain.po.User;
import com.tre.jdevtemplateboot.exception.SystemExceptionAuthorization;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 14:42
 **/
@Component
public class CurrentUserMethodArgumentResolver  implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        //如果参数类型是User并且有CurrentUser注解则支持
        if (methodParameter.getParameterType().isAssignableFrom(User.class) &&
                (methodParameter.hasParameterAnnotation(CurrentUser.class))) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception{

        //System.out.println("·请求所需要的权限："+ methodParameter.getParameterAnnotation(CurrentUser.class).value());
        //此处处理角色和权限的校验
        //根据访问方法注解中的角色和权限，和从数据库中取的权限是否包含，进行判断。


        //取出鉴权时存入的登录用户Id
        String currentUserId = (String) nativeWebRequest.getAttribute(SysConstantToken.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);

        User user = new User();
        user.setId(currentUserId);
        user.setUserName(currentUserId);
        user.setRole(1);
        if(user.getRole() == 1 || user.getRole() == 2){
            return user;
        }
        throw new SystemExceptionAuthorization(SysConstantInfo.SERVER_REQUEST_AUTHORIZATION_CODE,SysConstantInfo.SERVER_REQUEST_AUTHORIZATION_MSG);
    }
}
