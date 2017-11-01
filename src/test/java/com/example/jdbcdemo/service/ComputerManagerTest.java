package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.jdbcdemo.domain.Computer;

public class ComputerManagerTest {
	
	ComputerManagerJDBC computerManager = new ComputerManagerJDBC();
	
	@Test 
	public void checkConnection() {
		assertNotNull(computerManager.getConnection());
	}
	
	@Test
	public void checkAdding() {

		Computer computer = new Computer("Kabby", 1024, "Ryzen 7", 1, "GTX1060", 1999.99);
		computerManager.addComputer(computer);
	}

	@Test
	public void checkUpdate() {
		int id = 1;
		computerManager.updateComputer(id);
	}

	@Test
	public void checkDelete() {
		int id = 1;
		computerManager.deleteComputer(id);
	}
}

