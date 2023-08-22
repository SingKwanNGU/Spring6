package com.atguigu.spring6.resources;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

//访问类路径下的资源
public class ClassPathResourceDemo {
    public static void main(String[] args) {
        loadClassPathResource("atguigu.txt");
    }

    public static void loadClassPathResource(String path){
        //创建ClassPathResource对象
        ClassPathResource resource=new ClassPathResource(path);
        //获取资源信息
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
        try {
            InputStream is = resource.getInputStream();
            byte[] bytes=new byte[1024];
            while (is.read(bytes)!=-1){
                System.out.println(new String(bytes).trim());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
