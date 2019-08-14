package com.tre.jdevtemplateboot.annotatiaon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description: 检查用户是否被授权
* @Param:
* @return:
* @Author: JDev
* @Date: 2019/03/27
**/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    String value() default "";
}
