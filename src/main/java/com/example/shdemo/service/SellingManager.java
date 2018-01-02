package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Producer;

public interface SellingManager {

    void addClient(Person person);

    List<Person> getAllClients();

    void deleteClient(Person person);

    Person findClientByPin(String pin);

    void addProducer(Producer producer);

    List<Producer> getAllProducers();

    void deleteProducer(Producer producer);

    Producer findProducerById(Long id);

    Long addNewComputer(Computer computer);

    List<Computer> getAvailableComputers();

    void disposeComputer(Person person, Computer computer);

    Computer findComputerById(Long id);

    public Long getLastAddedComputerId();

    List<Computer> getOwnedComputers(Person person);

    void sellComputer(Long personId, Long computerId);

}
