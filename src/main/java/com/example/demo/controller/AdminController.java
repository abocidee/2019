package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.repository.UserRespository;

@RestController
public class AdminController {
	
	@Autowired
	private UserRespository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
    	String pwd =user.getPassword();
    	String encrytPwd = passwordEncoder.encode(pwd);
    	user.setPassword(encrytPwd);
    	userRepository.save(user);
		return "user add successfully";
	}
    
    @GetMapping(value="/login")
    public String login(ModelAndView m) {
    	m.setViewName("login1");
    	return m.toString();
    }
}
