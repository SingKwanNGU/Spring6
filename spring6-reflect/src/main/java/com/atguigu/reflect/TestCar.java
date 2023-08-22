package com.atguigu.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestCar {

    //1.获取class对象多种方式
    @Test
    public void test01() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.类名.class获取
        Class clazz1 = Car.class;

        //2.对象.getClass()方法
        Class clazz2 = new Car().getClass();

        //3.Class.forName("全路径")
        Class clazz3 = Class.forName("com.atguigu.reflect.Car");

        Car car1 = (Car)clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car1);
    }

    //2.获取构造方法
    @Test
    public void test02() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Car.class;
        //获取无参构造器
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        Constructor declaredConstructor2 = clazz.getDeclaredConstructor(String.class, int.class, String.class);
        Car car = (Car)declaredConstructor2.newInstance("五菱宏光", 5, "粉色");
        System.out.println(car);
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName()+constructor.getParameterCount());
        }
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor.getName()+constructor.getParameterCount());
        }
    }

    //3.获取属性
    @Test
    public void test03() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class clazz = Car.class;
        Car car=(Car) clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());

            if("name".equals(declaredField.getName())){
                declaredField.setAccessible(true);
                declaredField.set(car,"五菱宏光");
            }else if("age".equals(declaredField.getName())){
                declaredField.setAccessible(true);
                declaredField.set(car,5);
            }else{
                declaredField.setAccessible(true);
                declaredField.set(car,"粉色");
            }

        }
        System.out.println(car);

    }

    //4.获取方法
    @Test
    public void test04() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Car.class;
        Car car=(Car) clazz.getDeclaredConstructor().newInstance();
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("-----------------------------------");
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            System.out.println(method.getName());
            if(method.getName().equals("toString")){
                String invoke = (String) method.invoke(car);
                System.out.println("toString方法执行了:"+invoke);
            }
        }

    }
}
