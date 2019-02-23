package com.example.demo.persondao;

import java.util.List;

import javax.persistence.EntityManagerFactory;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.moduledao.Person;
@Repository
@Transactional
public class UserDao {
	 @Autowired
		private SessionFactory factory ;
	    
	    public void saveUser(User user) {
	    	getSession().save(user);
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

		public void save(User user) {
			// TODO Auto-generated method stub
			getSession().save(user);
		}
}
