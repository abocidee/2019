package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.persondao.PersonDao;

@RestController
public class PersonController {
   @Autowired
   private PersonDao dao;
   
   
   @RequestMapping("/hello")
   public String sayHello() {
	   return "hello universe";
   }
   @PostMapping("/savePerson")
   public String save(@RequestBody Person person) {
	   dao.savePerson(person);
	   return "success";
	   
   }
   @GetMapping("/getAllPersons")
   public List<Person>  getAllPersons(){
	   return dao.getPersons();
   }
   @GetMapping("/")
   public String getIndex() {
	 return "index";
   }
}
