package com.myjdbc.datagrid.annotation;

import java.lang.annotation.*;


/**
 * @Author 陈文
 * @Date 2019/12/1  15:29
 * @Description 拥有该注解代表该属性为容器展示类，会被传递到前台并显示
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DatagridColumn {

    String fieldId() default "";

    String title() default "";

    boolean hidden() default false;

    int rowspan() default 1;

    int colspan() default 1;

}
