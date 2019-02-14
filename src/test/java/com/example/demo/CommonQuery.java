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
public class CommonQuery {
  
	@Autowired
	public JdbcTemplate jdbcTemplate;
   
	/**
     * 显示map
     * @param sql
     */
	public  void showTableMap(String sql) {
		List<Map<String,Object>> list =jdbcTemplate.queryForList(sql);
		System.out.println("sql:"+sql);
		System.out.println("-----------------------------------------------------");
		list.forEach((entry)->System.out.println(entry));
	}
	/**
	 * The Maximum Value for a Column
	 */
	@Test
	public void maxColumn() {
		String sql="SELECT MAX(article) AS article FROM shop";
		showTableMap(sql);
	}
	
	/**
	 * The Row Holding the Maximum of a Certain Column
	 */
	@Test
	public void certainColumn() {
		String sql="SELECT article, dealer, price\n" + 
				"FROM   shop\n" + 
				"WHERE  price=(SELECT MAX(price) FROM shop);";
		showTableMap(sql);
	}
	
	/**
	 * The Row Holding the Maximum of a Certain Column
	 */
	@Test
	public void certainColumn2() {
		String sql="SELECT s1.article, s1.dealer, s1.price\n" + 
				"FROM shop s1\n" + 
				"LEFT JOIN shop s2 ON s1.price < s2.price\n" + 
				"WHERE s2.article IS NULL";
		showTableMap(sql);
		
		String sql2 = "\n" + 
				"SELECT article, dealer, price\n" + 
				"FROM shop\n" + 
				"ORDER BY price DESC\n" + 
				"LIMIT 1";
		showTableMap(sql2);
	}
	
	/**
	 * Maximum of Column per Group
	 */
	@Test
	public void MaxColGroup() {
		String sql = "SELECT article,MAX(price) AS price "
				+ "FROM shop "
				+ "GROUP BY article "
				+ "ORDER BY article ";
		showTableMap(sql);
	}
	
	/**
	 * The Rows Holding the Group-wise Maximum of a Certain Column
	 */
	@Test
	public void groupWiseMax() {
		String sql= "SELECT article, dealer, price\n" + 
				"FROM   shop s1\n" + 
				"WHERE  price=(SELECT MAX(s2.price)\n" + 
				"FROM shop s2\n" + 
				"              WHERE s1.article = s2.article)\n" + 
				"ORDER BY article;\n" + 
				"";
		showTableMap(sql);
		
		String sql2 ="SELECT s1.article, dealer, s1.price\n" + 
				"FROM shop s1\n" + 
				"JOIN (\n" + 
				"  SELECT article, MAX(price) AS price\n" + 
				"  FROM shop\n" + 
				"  GROUP BY article) AS s2\n" + 
				"  ON s1.article = s2.article AND s1.price = s2.price\n" + 
				"ORDER BY article";
		showTableMap(sql2);
		
		String sql3= "SELECT s1.article, s1.dealer, s1.price\n" + 
				"FROM shop s1\n" + 
				"LEFT JOIN shop s2 ON s1.article = s2.article AND s1.price < s2.price\n" + 
				"WHERE s2.article IS NULL\n" + 
				"ORDER BY s1.article";
		showTableMap(sql3);
		//window function
		String sql4 =" WITH s1 AS (\n" + 
				"   SELECT article, dealer, price,    RANK() OVER (PARTITION BY article\n" + 
				"                           ORDER BY price DESC\n" + 
				") AS `Rank`\n" + 
				"FROM shop\n" + 
				")\n" + 
				"SELECT article, dealer, price\n" + 
				"FROM s1\n" + 
				"  WHERE `Rank` = 1\n" + 
				"ORDER BY article";
		showTableMap(sql4);
	}
}
