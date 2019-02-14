package com.example.demo;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {
	
	  @Autowired
	  public JdbcTemplate jdbcTemplate;
	  /**
	     * 显示map
	     * @param sql
	     */
		public  void showTableMap(String sql) {
			List<Map<String,Object>> list =jdbcTemplate.queryForList(sql);
			System.out.println("----------begin---------------------------------------------------\n");
			list.forEach((entry)->System.out.println(entry));
			System.out.println();
			System.out.println("-----------end----------------------------------------------------\n");
		}
   
 
   /**
    * create event table
    */
	@Test
	public void createTable() {
		String sql ="CREATE TABLE event (name VARCHAR(20), date DATE, type VARCHAR(15), remark VARCHAR(255))";
	     jdbcTemplate.execute(sql);
	     
	}
	/**
	 * inner join date
	 */
	@Test
	public void InnerJoinDate() {
		String sql = "SELECT pet.name,pet.owner,TIMESTAMPDIFF(YEAR,birthDate,date) as age,event.remark from pet inner join event on pet.name = event.name  where event.type = 'litter'";
	     showTableMap(sql);
	}
	/**
	 * 两个sql 不是在同一个session下
	 */
	@Test
	public void userDefinedVar() {
		String sql ="SELECT @min_price:=MIN(price),@max_price:=MAX(price) FROM shop";
	    Map<String,Object> map =jdbcTemplate.queryForMap(sql);
	    System.out.println(map);
	    String sql2 ="SELECT * FROM shop WHERE price=@min_price OR price=@max_price";
	    Map<String,Object> map2 =jdbcTemplate.queryForMap(sql2);
	    System.out.println(map2);
	}
}
