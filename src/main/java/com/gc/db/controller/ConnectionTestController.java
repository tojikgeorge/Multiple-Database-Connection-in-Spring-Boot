package com.gc.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gc.db.service.DBService;

@RestController
@RequestMapping("/api")
public class ConnectionTestController {
	
	@Autowired
	DBService service;
	
	@GetMapping("/show")
	public String getResult()
	{
		return service.getResult();
	}

}
