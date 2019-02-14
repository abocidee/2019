package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonFormat;


@Entity(name ="person")
public class Person {
	@Id
	@GeneratedValue
  private int id;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
  private Date dob;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
  
}
