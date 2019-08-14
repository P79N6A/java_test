package com.tre.jdevtemplateboot.annotatiaon;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @Description:  在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象 
* @Param:  
* @return:  
* @Author: JDev
* @Date: 2019/03/27
**/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
    String value() default "";
}
