package com.gc.db.two.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gc.db.two.entity.TestTwoEntity;


@Repository
public class TestTwoRepoImpl implements TestTwoRepo {

	@Qualifier("sessionFactory")
	@Autowired
	private SessionFactory factory;
	
	@Override
	public List<TestTwoEntity> getTestResult() {
		 Session session =  factory.openSession();
		try {
			
	   
		Query<TestTwoEntity> query = session.createNamedQuery("TestTwoEntity.findAll", TestTwoEntity.class);
		List<TestTwoEntity> students = query.getResultList();
		return students;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			session.close();
		}
		return Collections.EMPTY_LIST;
	}

}
