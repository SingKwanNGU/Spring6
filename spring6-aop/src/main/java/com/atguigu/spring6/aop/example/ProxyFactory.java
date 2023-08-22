package com.atguigu.spring6.aop.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {
    //目标对象传入
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //创建一个动态代理对象并返回此代理对象
    public Object getProxy(){
        ClassLoader classLoader=target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler=new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));
                Object result = method.invoke(target, args);
                System.out.println("[动态代理][日志] "+method.getName()+"，结果："+ result);
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }
}
