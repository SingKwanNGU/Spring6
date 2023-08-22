package com.atguigu.spring6.iocxml.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJdbc {

    @Test
    public void demo2(){
        ApplicationContext context=new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DruidDataSource druidDataSource = context.getBean(DruidDataSource.class);
        System.out.println(druidDataSource.getUrl());
    }

    @Test
    public void demo1(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
}
