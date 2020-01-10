package com.dubbo.gmall.pms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  1、配置整合dubbo
 *  2、配置整合MybatisPlus
 *  3、配置logstash整合
 *      第三方jar包导入
 *      导致日志配置
 *      在kibana里面建立好日志索引，就可以可视化检索
 */
@EnableDubbo
@MapperScan(basePackages = "com.dubbo.gmall.pms.mapper")
@SpringBootApplication
public class GmallPmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallPmsApplication.class, args);
    }

}
