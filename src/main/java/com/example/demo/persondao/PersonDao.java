package com.example.demo.persondao;


import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.moduledao.Person;

@Repository
@Transactional
public class PersonDao {
    @Autowired
	private SessionFactory factory ;
    
    public void savePerson(Person person) {
    	getSession().save(person);
    }
	
    @SuppressWarnings("unchecked")
	public List<Person>  getPersons(){
    	return getSession().createCriteria(Person.class).list();
    }
	private Session getSession() {
		Session session =factory.getCurrentSession();
		if(session == null){
			session = factory.openSession();
		}
		
		return session ;
	}
	
	private EntityManagerFactory getFactoryemc() {
		return getSession().getEntityManagerFactory();
	}
}
