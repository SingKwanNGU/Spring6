package com.atguigu.spring6;

public class User {
    private String name;
    private Person person;

    public User(){
        System.out.println("0:无参数构造方法执行了.....");
    }

    public void add(){
        System.out.println("add....");
    }
}
