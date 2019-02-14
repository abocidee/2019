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
public class PetTest {
	@Autowired
	public JdbcTemplate jdbcTemplate;
   
	/**
     * 显示map
     * @param sql
     */
	public  void showTableMap(String sql) {
		List<Map<String,Object>> list =jdbcTemplate.queryForList(sql);
		list.forEach((entry)->System.out.println(entry));
	}
	
	/**
	 * 插入整体数据到table pet 中
	 */
	@Test
	public void test3() {
		String sql = "insert into pet values('lunongyun','diane','dog','m','1959-02-23',null)";
		jdbcTemplate.execute(sql);
	}

	@Test
	public void updateBirthDate() {
		StringBuilder sb = new StringBuilder();
		String birthDate = "2009-08-31";
		sb.append("update pet set birthDate = '" + birthDate + "' where name ='HAROLD'");
		jdbcTemplate.execute(sb.toString());
	}

	/**
	 * and or keywords condition test
	 */
	@Test
	public void selectWithAnd() {

		StringBuilder sb = new StringBuilder();
		sb.append("select name,species,birthDate ");
		sb.append("from pet ");
		sb.append("where (name = 'Bowser' and species = 'dog') ");
		sb.append("or (name = 'claws' and species ='dog') ");
		sb.append("order by species ,birthDate  desc");

	}

	/**
	 * calculate age by curdate() function and timestampdiff()
	 * 
	 */
	@Test
	public void calTimeDiff() {

		StringBuilder sb = new StringBuilder();
		sb.append("select name , birthDate,curDate(), ");
		sb.append("TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) as age  ");
		sb.append("from pet ");
		sb.append("order by age ");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
		for (Map<String, Object> entry : list) {
			System.out.println(entry);
		}
	}

	/**
	 * calculate how long does the pet live
	 */
	@Test
	public void calLifeCycle() {

		StringBuilder sb = new StringBuilder();
		sb.append("select name, ");
		sb.append("birthDate, ");
		sb.append("deathDate, ");
		sb.append("timestampdiff(year,birthdate,deathdate) as live ");
		sb.append("from pet  ");
		sb.append("where deathdate is not null  ");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
		for (Map<String, Object> entry : list) {
			System.out.println(entry);
		}

	}
	
	/**
	 * date add function use date_add   interval 
	 * 
	 */
	@Test
	public void dateAdd() {
		     
		StringBuilder sb =new StringBuilder();
		sb.append("SELECT name, ");
		sb.append("birthdate ");
		sb.append("FROM pet ");
		sb.append("WHERE MONTH(birthdate) = ");
		sb.append("MONTH(DATE_ADD(CURDATE(),INTERVAL 2 MONTH)) ");
		List<Map<String,Object>>  list =jdbcTemplate.queryForList(sb.toString());
		list.forEach((map)->System.out.println(map));
		}
	
	/**
	 * DATE ADD ONE MONTH USE   MOD  FUNCTION 
	 * 
	 */
	@Test
	public void dateAddWithMod() {
		StringBuilder sb =new StringBuilder();
		sb.append("SELECT name, ");
		sb.append("birthdate ");
		sb.append("FROM pet ");
		sb.append("WHERE MONTH(birthdate) = ");
		sb.append("MOD(MONTH(CURDATE()),12)+1 ");
		List<Map<String,Object>>  list =jdbcTemplate.queryForList(sb.toString());
		list.forEach((map)->System.out.println(map));
	}
	
	/**
	 * the regular Expression with case senctive 
	 */
	@Test
	public void serachSensitive() {
		
		String sql ="SELECT * FROM pet WHERE  REGEXP_LIKE(name ,'fy$','c')";
        showTableMap(sql);
		
	}
	
	/**
	 * The splecit bit numbers searching of regular expression 
	 */
	@Test
	public void  bitNumbers() {
		String sql ="select * from pet where REGEXP_LIKE(name,'^.....$')";
		showTableMap(sql);
	}
    
	
	/**
	 * test gropu by clause
	 * the sql_mode must be ONLY_FULL_GROUP_BY if the sql_mode ='';
	 * we could do expressions like this 
	 * select name ,count(*) 
       -> from pet;
	 */
	@Test
	public void testGRoupBy() {
		String sql ="select species,sex,count(*) from pet where species = 'dog' or species ='cat' group by species,sex";
	    showTableMap(sql);
		
	}
	
	
}
