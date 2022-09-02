package com.gc.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gc.db.one.entity.TestEntity;
import com.gc.db.one.repository.TestRepo;
import com.gc.db.two.entity.TestTwoEntity;
import com.gc.db.two.repository.Test2Repo;
import com.gc.db.two.repository.TestTwoRepo;

@Service
public class DBService {
	
	@Autowired
	TestRepo test1;
	@Autowired
	Test2Repo test2;
	@Autowired
	TestTwoRepo test3;
	
	@Transactional
	public String getResult()
	{
		String result1 = "";
		
		for( TestEntity one : test1.findAllDetails())
		{
			result1 = result1 + one.getName()+" ";
		}
		
		for( TestTwoEntity two : test2.findAllDetails())
		{
			result1 = result1 + two.getName()+" ";
		}
		
		for( TestTwoEntity two : test3.getTestResult())
		{
			result1 = result1 + " -Session- " + two.getName()+"  ";
		}
		
		return result1;
	}

}
