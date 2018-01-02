package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.example.shdemo.domain.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String NAME_1 = "Marcin";
	private final String PIN_1 = "1234";

	private final String NAME_2 = "Dawid";
	private final String PIN_2 = "4321";

	private final String MODEL_1 = "RAZOR GT";
	private final String MAKE_1 = "MSI";

	private final String MODEL_2 = "XPS2000";
	private final String MAKE_2 = "Dell";

	@Test
	public void addClientCheck() {

		List<Person> retrievedClients = sellingManager.getAllClients();

		// If there is a client with PIN_1 delete it
		for (Person client : retrievedClients) {
			if (client.getPin().equals(PIN_1)) {
				sellingManager.deleteClient(client);
			}
		}

		Person person = new Person();
		person.setFirstName(NAME_1);
		person.setPin(PIN_1);
		person.getComputers().add(new Computer("MSI","RazorX"));
		// ... other properties here

		// Pin is Unique
		sellingManager.addClient(person);

		Person retrievedClient = sellingManager.findClientByPin(PIN_1);
        System.out.println(retrievedClient.getId()+retrievedClient.getFirstName());
		assertEquals(NAME_1, retrievedClient.getFirstName());
		assertEquals(PIN_1, retrievedClient.getPin());
		// ... check other properties here
	}

	@Test
	public void addComputerCheck() {

		Computer computer = new Computer();
		//computer.setProducent(new Producer (MAKE_1));
		computer.setModel(MODEL_1);
		// ... other properties here

		Long computerId = sellingManager.addNewComputer(computer);

		Computer retrievedComputer = sellingManager.findComputerById(computerId);
		//assertEquals(MAKE_1, retrievedComputer.getProducent());
		assertEquals(MODEL_1, retrievedComputer.getModel());
		// ... check other properties here

	}

	@Test
	public void sellComputerCheck() {

		Person person = new Person();
		person.setFirstName(NAME_2);
		person.setPin(PIN_2);

		sellingManager.addClient(person);

		Person retrievedPerson = sellingManager.findClientByPin(PIN_2);

		Computer computer = new Computer();
		//computer.setProducent(new Producer(MAKE_2));
		computer.setModel(MODEL_2);

		Long computerId = sellingManager.addNewComputer(computer);

		sellingManager.sellComputer(retrievedPerson.getId(), computerId);

		List<Computer> ownedcomputers = sellingManager.getOwnedComputers(retrievedPerson);

		assertEquals(1, ownedcomputers.size());
		//assertEquals(MAKE_2, ownedcomputers.get(0).getProducent());
		assertEquals(MODEL_2, ownedcomputers.get(0).getModel());
	}

	// @Test -
	public void disposecomputerCheck() {
		// Do it yourself
	}

}
