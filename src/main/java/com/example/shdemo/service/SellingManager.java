package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Person;

public interface SellingManager {
	
	void addClient(Person person);
	List<Person> getAllClients();
	void deleteClient(Person person);
	Person findClientByPin(String pin);
	
	Long addNewComputer(Computer computer);
	List<Computer> getAvailableComputers();
	void disposeComputer(Person person, Computer computer);
	Computer findComputerById(Long id);

	List<Computer> getOwnedComputers(Person person);
	void sellComputer(Long personId, Long computerId);

}
