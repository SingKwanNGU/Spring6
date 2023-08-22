package com.atguigu.spring6.aop.example;

public class TestCal {
    public static void main(String[] args) {
        //1创建代理对象工厂并传入目标对象
        ProxyFactory proxyFactory=new ProxyFactory(new CalculatorImpl());
        //2.使用getProxy（）创建代理对象并返回以供使用，由于返回的是Object类型代理，需要进行类型转换为目标对象所需的类型。
        //总结：代理对象是一种增强型的目标对象。代理对象也实现了目标对象的所有接口，还实现了新的额外功能。
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1,2);
    }
}
