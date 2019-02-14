package com.example.demo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestLogUtil {
  private static TestLogUtil logUtil = new TestLogUtil();
	private TestLogUtil () {
		
	}
  public static synchronized TestLogUtil getInstance() {
	   return logUtil;
  }
  
	
}
