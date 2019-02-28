package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
@Transactional
@Repository
public interface UserRespository  extends JpaRepository<User,Integer>{

	
	public  List<User> findAll() ;

	
	public <S extends User> S save(S entity) ;

	
	public User getOne(Integer id);
 
	
}
