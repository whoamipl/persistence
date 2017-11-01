package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.jdbcdemo.domain.Computer;

import java.util.List;

public class ComputerManagerTest {
	
	ComputerManagerJDBC computerManager = new ComputerManagerJDBC();
	Computer computerAdd = new Computer("Kabby", 1024, "Ryzen 7", 1, "GTX1060", 1999.99);
	Computer computerUpdate = new Computer("Kabby", 4096, "Ryzen 7", 1, "GTX1080Ti", 1999.99);


	@Test 
	public void checkConnection() {
		assertNotNull(computerManager.getConnection());
	}
	
	@Test
	public void checkAdding() {

		computerManager.addComputer(computerAdd);
	}

	@Test
	public void checkGetAllComputers() {

		computerManager.addComputer(computerAdd);
		List<Computer> computers = computerManager.getAllComputers();
		assertEquals(computerAdd, computers.get(0));
	}

	@Test
	public void checkUpdate() {

		int id = 1;
		computerManager.addComputer(computerAdd);
		computerManager.updateComputer(id,computerUpdate);
		assertEquals(computerManager.getComputerById(id).getCpu(), computerUpdate.getCpu());
		assertEquals(computerManager.getComputerById(id).getGpu(), computerUpdate.getGpu());
		assertEquals(computerManager.getComputerById(id).getHdd(), computerUpdate.getHdd());
		assertEquals(computerManager.getComputerById(id).getModel(), computerUpdate.getModel());
		assertEquals(computerManager.getComputerById(id).getPrice(), computerUpdate.getPrice());
		assertEquals(computerManager.getComputerById(id).getRam(), computerUpdate.getRam());

	}

	@Test
	public void checkDelete() {

		int id = 1;
		assertEquals(computerManager.deleteComputer(id), 0, 1);
	}

	@Test
	public void checkDeleteAllComputer() {
		computerManager.deleteAllComputers();
	}
}

