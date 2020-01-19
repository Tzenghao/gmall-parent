package com.dubbo.gmall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * VO: (View Object/Value Object) 视图对象，把专门交给前端的数据封装成vo;或者是前端传给后台的数据。
 * DAO: (Database Access Object) 数据库访问对象；专门用来对数据库进行crud的对象。XxxMapper
 * POJO: (Plain Old Java Object) 古老单纯的Java对象。javaBean(封装数据的)
 * DO: (Data Object) 数据对象---POJO (DataBase Object) 数据对象(专门用来封装数据库表的实体类)
 * TO: (Transfer Object) 传输对象
 *      1）服务之间互调，为了数据传输封装对象
 *      2）service之间互调，为了数据传输封装对象
 * DTO: (Data Transfer Object) 和TO一样
 *
 * exclude = {DataSourceAutoConfiguration.class}
 *      不进行数据源的自动配置，避免导入了mysql依赖但是没有配置而报错。
 *
 * 如果导入的依赖，引入了一个自动配置场景
 *      1）这个场景自动配置默认生效，我们就必须配置它
 *      2）不想配置的解决办法：
 *          1、 引入的时候排除这个场景依赖
 *          2、 通过SpringBootApplication的exclude参数排除掉这个场景的自动配置类
 *
 *  rpc远程调用需要给实体类实现序列化接口
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GmallAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallAdminWebApplication.class, args);
    }

}
