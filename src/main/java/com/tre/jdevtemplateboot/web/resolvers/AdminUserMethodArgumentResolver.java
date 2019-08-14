package com.tre.jdevtemplateboot.web.resolvers;

import com.tre.jdevtemplateboot.annotatiaon.AdminUser;
import com.tre.jdevtemplateboot.domain.po.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @description:
 * @author: JDev
 * @create: 2019-03-27 14:40
 **/
@Component
public class AdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {



    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        //如果参数类型是User并且有AdminUser注解则支持
        if (methodParameter.getParameterType().isAssignableFrom(User.class) &&
                (methodParameter.hasParameterAnnotation(AdminUser.class))) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        return null;
    }
}
