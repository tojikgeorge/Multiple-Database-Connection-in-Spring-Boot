package com.gc.db.one.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test")
@Getter
@Setter
public class TestEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    private String name;
}
