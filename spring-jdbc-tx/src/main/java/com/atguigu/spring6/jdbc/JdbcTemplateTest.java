package com.atguigu.spring6.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSelectObject(){
        //写法一：
//        String sql="select * from t_emp where id=?";
//        Object empResult = jdbcTemplate.queryForObject(sql,(rs, rowNum) ->{
//            Emp emp=new Emp();
//            emp.setId(rs.getInt("id"));
//            emp.setName(rs.getString("name"));
//            emp.setAge(rs.getInt("age"));
//            emp.setSex(rs.getString("sex"));
//            return emp;
//        }  , 1);
//        System.out.println(empResult);
//        写法二
        String sql="select * from t_emp where id=?";
        Emp emp=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Emp.class),1);
        System.out.println(emp);
    }

    @Test
    public void testSelectList(){
        String sql="select * from t_emp";
        List<Emp> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(list);
    }

    @Test
    public void testSelectValue(){
        String sql="select count(*) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    @Test
    public void testUpdate(){
        //1添加操作
//        String sql="insert into t_emp values(null,?,?,?)";
////        int rows = jdbcTemplate.update(sql, "东方不败", 20, "未知");
////        int rows = jdbcTemplate.update(sql, "岳不群", 40, "未知");
//        int rows = jdbcTemplate.update(sql, "林平之", 20, "未知");
//        System.out.println(rows);

//        2.修改操作
//        String sql="update t_emp set name=? where id=?";
//        int rows = jdbcTemplate.update(sql, "令狐冲", 3);
//        System.out.println(rows);

//        3.删除操作
//        String sql="delete from t_emp where id=?";
//        int rows=jdbcTemplate.update(sql,3);
//        System.out.println(rows);

//        4.查询操作
        //查询返回一个对象
        //查询返回一个List集合
        //查询返回单个值

    }
}
