package com.tre.jdevtemplateboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tre.jdevtemplateboot.web.interceptor.JDevRequestInterceptor;
import com.tre.jdevtemplateboot.web.interceptor.JDevRequestJwtInterceptor;
import com.tre.jdevtemplateboot.web.resolvers.AdminUserMethodArgumentResolver;
import com.tre.jdevtemplateboot.web.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Web服务配置中心
 * @author: JDev
 * @create: 2018-11-14 11:25
 **/
@Configuration
public class WebMvcAppConfig  extends WebMvcConfigurationSupport {

    @Autowired
    private JDevRequestInterceptor jDevRequestInterceptor;

    @Autowired
    private JDevRequestJwtInterceptor jDevRequestJwtInterceptor;

    @Autowired
    private AdminUserMethodArgumentResolver adminUserMethodArgumentResolver;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    /**
    * @Description:  请求服务资源的认证拦截
    * @Param: [registry]
    * @return: void
    * @Author: JDev
    * @Date: 2018/11/14
    **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //Redis版本认证拦截和Token延期
        //InterceptorRegistration ir = registry.addInterceptor(jDevRequestInterceptor);

        //JWT版本认证拦截
        InterceptorRegistration ir = registry.addInterceptor(jDevRequestJwtInterceptor);
        ir.addPathPatterns("/**")
                .excludePathPatterns("/user/login/**", "/upload/**", "/static/**");

    }

    /** 
    * @Description: 自定义参数解析（自定义注解需要在此处添加）
    * @Param: [argumentResolvers] 
    * @return: void 
    * @Author: JDev
    * @Date: 2019/03/27 
    **/
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(adminUserMethodArgumentResolver);
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }

    /**
     * @Description:  访问静态资源
     * @Param: [registry]
     * @return: void
     * @Author: JDev
     * @Date: 2018/12/24
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //上传文件路径
        if(!registry.hasMappingForPattern("/upload/**")){
            registry.addResourceHandler("/upload/**").addResourceLocations("file:upload\\");
        }

        //静态资源路径
        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
    }

    /**
     * @Description: 序列化字符 NULL返回指定值
     * @Param: [converters]
     * @return: void
     * @Author: JDev
     * @Date: 2019/01/24
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,   //字符串null返回空字符串
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(fastJsonConfig);

        // 中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8); //设定json格式且编码为UTF-8
        converter.setSupportedMediaTypes(mediaTypes);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

}
