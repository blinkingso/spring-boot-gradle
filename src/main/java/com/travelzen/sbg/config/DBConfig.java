package com.travelzen.sbg.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.travelzen.sbg.properties.DBProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author andrew
 * @createtime 2017-09-05
 * @IDE INTELLIJ IDEA
 **/
@Configuration
public class DBConfig {

    @Autowired
    private DBProperties dbProperties;

    @Bean("dataSource")
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbProperties.getDriverClassName());
        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setMaxActive(Integer.valueOf(dbProperties.getMaxActive()));
        dataSource.setUsername(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());
        dataSource.setPassword("#java021");
        return dataSource;
    }

}
