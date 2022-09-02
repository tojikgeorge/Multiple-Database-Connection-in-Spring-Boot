package com.gc.db.two.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "TestTwoEntity")
@Table(name = "test")
@NamedQueries({
	@NamedQuery(name = "TestTwoEntity.findAll",query = "select e from TestTwoEntity e ")})
public class TestTwoEntity implements Serializable{

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	    private int id;
	 @Column(name = "name")
	    private String name;
	 
	 public static TestTwoEntity from()
	 {
		 return new TestTwoEntity();
	 }
}
