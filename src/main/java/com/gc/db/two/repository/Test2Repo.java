package com.gc.db.two.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gc.db.one.entity.TestEntity;
import com.gc.db.two.entity.TestTwoEntity;

@Repository
public interface Test2Repo extends JpaRepository<TestTwoEntity, Long> {
	

	@Query("select t from TestTwoEntity t ")
	List<TestTwoEntity> findAllDetails();

}
