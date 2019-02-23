package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.persondao.UserDao;
import com.example.demo.repository.UserRespository;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {
	
	/*@Autowired
	private UserRespository userRepository;*/
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
    	String pwd =user.getPassword();
    	String encrytPwd = passwordEncoder.encode(pwd);
    	user.setPassword(encrytPwd);
    	userDao.save(user);
		return "user add successfully";
	}
}
