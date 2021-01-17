package com.garrison.campusstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@SpringBootApplication
@MapperScan("com.garrison.campusstore.dao")
@EnableTransactionManagement//开启事务支持
public class SpringBootCampusStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCampusStoreApplication.class, args);
    }
    //配置C3P0连接池
    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        DataSource dataSource= DataSourceBuilder.create()
                .type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
        return dataSource;
    }
}




