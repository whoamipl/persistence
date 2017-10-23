package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.util.List;

import com.example.jdbcdemo.domain.Computer;

public interface ComputerManager {
	
	public int addComputer(Computer computer);
	public List<Computer> getAllComputers();
	
}
