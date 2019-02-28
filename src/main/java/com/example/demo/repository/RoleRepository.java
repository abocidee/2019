package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Role;
@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	
	public Optional<Role> findById(Integer id);


	public List<Role> findAll();

	
	   
}
