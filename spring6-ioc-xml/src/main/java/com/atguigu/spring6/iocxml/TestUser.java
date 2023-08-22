package com.atguigu.spring6.iocxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//获取bean的三种方式
public class TestUser {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
        //1.根据bean id获取bean实例对象。
        User user1 =(User)context.getBean("user");
        System.out.println("1.根据id获取bean对象:"+user1);

        //2.根据类型获取bean实例对象 根据类型获取的话 要求IOC容器中的指定类型的bean有且只有一个
        User user2 = context.getBean(User.class);
        System.out.println("2.根据类型获取bean对象:"+user2);

        //根据id和类型获取bean实例对象
        User user3 = context.getBean("user", User.class);
        System.out.println("3.根据id和类型获取bean对象:"+user3);
    }
}
