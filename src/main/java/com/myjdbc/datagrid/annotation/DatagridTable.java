package com.myjdbc.datagrid.annotation;

import java.lang.annotation.*;


/**
 * @Author 陈文
 * @Date 2019/12/1  15:29
 * @Description 1.如果是单行表头，可以不需要此注解
 * 2.即使是多表头，主类也不需要此注解，因为主类一定是最后一行加载的
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DatagridTable {

    //定义多表头加载时，该实体处理哪一行表头
    int index();

}
