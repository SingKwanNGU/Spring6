package com.atguigu.spring6.iocxml.lifecycle;

public class User {
    public User(){
        System.out.println("1 bean对象创建，调用无参数构造");
    }

    public  void InitMethod(){
        System.out.println("4 bean对象的初始化，调用指定的初始化方法");
    }

    public void destroyMethod(){
        System.out.println("7 bean对象的销毁，调用指定的销毁方法");
    }
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2 给bean对象设置属性值");
        this.name = name;
    }
}
