package com.dubbo.gmall.pms.config;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 配置数据源
 *  springboot引入某个场景，这个场景的组件就会自动配置好
 *      1）
 * @Author ZengHao
 * @Date 2020/1/10 14:16
 */
@Configuration
public class PmsDataSourceConfig {
    @Bean
    public DataSource dataSource() throws IOException, SQLException {
        //ResourceUtils资源工具类,这里用来获取配置文件
        File file = ResourceUtils.getFile("classpath:sharding-jdbc.yml");
        //创建数据源
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(file);
        return dataSource;
    }
}
