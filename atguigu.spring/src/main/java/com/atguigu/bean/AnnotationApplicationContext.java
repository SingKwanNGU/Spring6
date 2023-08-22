package com.atguigu.bean;

import com.atguigu.anno.Bean;
import com.atguigu.anno.Di;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationApplicationContext implements ApplicationContext{
    private  Map<Class,Object> beanFactory=new HashMap<>();
    //创建Map集合，存放bean对象
    private static String rootPath;
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    public AnnotationApplicationContext(String basePackage) {
//    public static void pathdemo1(String basePackage){
        try {
            String packagePath = basePackage.replaceAll("\\.", "\\\\");
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(),"utf-8");
                //获取包前面路径部分，字符串截取
                rootPath=filePath.substring(0,filePath.length()-packagePath.length());

                //包扫描
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loadDi();
    }



    //包扫描过程，实例化
    private  void loadBean(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.判断当前是否是文件夹
        if(file.isDirectory()){
            //2.获取文件夹里面所有里面
            File[] childrenFiles = file.listFiles();
            //3.判断文件夹里面为空，直接返回
            if(childrenFiles==null || childrenFiles.length==0){
                return;
            }
            //4.如果文件夹不为空，遍历文件夹所有内容
            for (File child : childrenFiles) {
                //4.1 遍历得到每个File对象，继续判断，如果还是文件夹，则递归。
                if(child.isDirectory()){
                    loadBean(child);
                }else {
                    //4.2 遍历得到File对象不是文件夹，是文件，判断文件上面是否有注解
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length() - 1);
                    //4.3 得到包路径+类名称部分-字符串截取
                    if(pathWithClass.contains(".class")){
                        //4.4 判断当前文件类型是否是.class类型
                        String allName = pathWithClass.replaceAll("\\\\", "\\.").replace(".class", "");
                        //4.5 如果是.class类型，把路径\替换成.  把.class去掉
                        //com.atguigu.service.UserServiceImpl
                        Class<?> clazz = Class.forName(allName);
                        //4.6 判断类上面是否有注解@Bean,如果有则进行实例化过程
                        //4.6.1获取类的class对象
                        if(!clazz.isInterface()){
                            //4.6.2判断是否为接口
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            //4.6.3判断是否有注解
                            if(annotation!=null){
                                //4.6.4 进行实例化
                                Object instance = clazz.getConstructor().newInstance();
                                //4.7把对象实例化后，存放到Map集合BeanFactory中
                                //4.7.1 判断当前类是否有接口，有接口让接口class作为Map的KEY
                                if(clazz.getInterfaces().length>0){
                                    beanFactory.put(clazz.getInterfaces()[0],instance);
                                }else {
                                    beanFactory.put(clazz,instance);
                                }
                            }
                        }


                    }

                }

            }

        }

    }

    private void loadDi() {
        //实例化对象在beanFactory的Map集合里面
        //1 遍历beanFactory的map集合
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            //2 获取map集合每个对象(value),每个对象属性获取到
            Object obj = entry.getValue();
            //获取class对象
            Class<?> clazz = obj.getClass();
            //获取每个对象属性
            Field[] declaredFields = clazz.getDeclaredFields();
            //3 遍历每个对象属性数组，得到每个属性
            for (Field field : declaredFields) {
                Di annotation = field.getAnnotation(Di.class);
                //4 判断属性上面是否有@Di注解
                if(annotation!=null){
                    field.setAccessible(true);
                    //如果私有属性，设置可以设置值
                    try {
                        field.set(obj,beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    //5 如果有@Di注解，把对象进行设置（注入）
                }

            }

        }



    }


//    public static void main(String[] args) {
//        ApplicationContext context=new AnnotationApplicationContext("com.atguigu");
//        context.getBean()
//    }
}
