package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
 @Autowired
 public  JdbcTemplate  jdbcTemplate;
    /**
     * 查看全部t6信息
     */
	@Test
	public void contextLoads() {
		  String sql = "select * from t6";
	        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
	        for (Map<String, Object> map : list) {
	            Set<Entry<String, Object>> entries = map.entrySet( );
	                if(entries != null) {
	                    Iterator<Entry<String, Object>> iterator = entries.iterator( );
	                    while(iterator.hasNext( )) {
	                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
	                    Object key = entry.getKey( );
	                    Object value = entry.getValue();
	                    System.out.println(key+":"+value);
	                }
	            }
	        }
	       System.out.println("hello"+list.get(0));
		
	}
	/**
	 * 查看用户，时间，现在日期，sql版本
	 */
     @Test
	 public void test2() {
       String sql = "select user(),now(),current_Date ,version()";
       List<Map<String,Object>> list= jdbcTemplate.queryForList(sql);
        list.forEach((s)->System.out.println(s));
     }
     
    
     
}

