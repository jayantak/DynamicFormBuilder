package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by kjayanta on 01/07/15.
 */

//@Configuration
//@Component
@PropertySource("classpath:application.properties")
public class Properties {

//    @Resource(name = "myProperties")
//    private Properties myProperties;

    @Value("${mysql.database}")
    String database1;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @PostConstruct
    public String databaseValue() {
        System.out.println(database1);
//        System.out.println(myProperties);

        return database1;
    }
}
