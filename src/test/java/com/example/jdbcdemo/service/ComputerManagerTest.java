package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.jdbcdemo.domain.Computer;

public class ComputerManagerTest {
	
	ComputerManagerJDBC computerManager = new ComputerManagerJDBC();
	
	@Test 
	public void checkConncetion() {
		assertNotNull(computerManager.getConnection());
	}
	
	@Test
	public void checkAdding() {
		Computer computer = new Computer();
	}
	
}
