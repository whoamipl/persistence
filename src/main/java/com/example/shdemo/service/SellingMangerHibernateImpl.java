package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.shdemo.domain.Producer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Person;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addClient(Person person) {
        person.setId(null);
        sessionFactory.getCurrentSession().persist(person);
    }

    @Override
    public void deleteClient(Person person) {
        person = (Person) sessionFactory.getCurrentSession().get(Person.class,
                person.getId());

        // lazy loading here
        for (Computer computer : person.getComputers()) {
            computer.setSold(false);
            sessionFactory.getCurrentSession().update(computer);
        }
        sessionFactory.getCurrentSession().delete(person);
    }

    @Override
    public List<Computer> getOwnedComputers(Person person) {
        person = (Person) sessionFactory.getCurrentSession().get(Person.class,
                person.getId());
        // lazy loading here - try this code without (shallow) copying
        List<Computer> cars = new ArrayList<Computer>(person.getComputers());
        return cars;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAllClients() {
        return sessionFactory.getCurrentSession().getNamedQuery("person.all")
                .list();
    }

    @Override
    public Person findClientByPin(String pin) {
        return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
    }

    @Override
    public void addProducer(Producer producer) {
        producer.setId(null);
        sessionFactory.getCurrentSession().persist(producer);
    }

    @Override
    public List<Producer> getAllProducers() {
        return sessionFactory.getCurrentSession().getNamedQuery("producer.all")
                .list();
    }

    @Override
    public void deleteProducer(Producer producer) {
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
                producer.getId());
        sessionFactory.getCurrentSession().delete(producer);
    }

    @Override
    public Producer findProducerById(Long id) {
        return (Producer) sessionFactory.getCurrentSession()
                .getNamedQuery("producer.byId").setString("id", id.toString()).uniqueResult();
    }


    @Override
    public Long addNewComputer(Computer computer) {
        computer.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(computer);
    }

    @Override
    public void sellComputer(Long personId, Long computerId) {
        Person person = (Person) sessionFactory.getCurrentSession().get(
                Person.class, personId);
        Computer computer = (Computer) sessionFactory.getCurrentSession()
                .get(Computer.class, computerId);
        computer.setSold(true);
        person.getComputers().add(computer);
    }


    @Override
    //@SuppressWarnings("unchecked")
    public List<Computer> getAvailableComputers() {
        return sessionFactory.getCurrentSession().getNamedQuery("computer.unsold")
                .list();
    }

    @Override
    public void disposeComputer(Person person, Computer computer) {

        person = (Person) sessionFactory.getCurrentSession().get(Person.class,
                person.getId());
        computer = (Computer) sessionFactory.getCurrentSession().get(Computer.class,
                computer.getId());

        Computer toRemove = null;
        // lazy loading here (person.getComputers)
        for (Computer aComputer : person.getComputers())
            if (aComputer.getId().compareTo(computer.getId()) == 0) {
                toRemove = aComputer;
                break;
            }

        if (toRemove != null)
            person.getComputers().remove(toRemove);

        computer.setSold(false);
    }

    @Override
    public Computer findComputerById(Long id) {
        return (Computer) sessionFactory.getCurrentSession().get(Computer.class, id);
    }

    @Override
    public Long getLastAddedComputerId() {
        return (Long) sessionFactory.getCurrentSession()
                .getNamedQuery("computer.lastAdded").setMaxResults(1).uniqueResult();

    }

}
