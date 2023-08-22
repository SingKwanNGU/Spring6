package com.atguigu.spring6.resources;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

//访问系统中的文件资源
public class FileSystemResourceDemo {
    public static void main(String[] args) {
        loadFileResource("atguigu.txt");
    }

    public static void loadFileResource(String path){
        //创建对象
        FileSystemResource resource=new FileSystemResource(path);

        //获取文件资源
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
        try {
            InputStream is = resource.getInputStream();
            byte[] bytes=new byte[1024];
            while ((is.read(bytes))!=-1){
                System.out.println(new String(bytes).trim());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
