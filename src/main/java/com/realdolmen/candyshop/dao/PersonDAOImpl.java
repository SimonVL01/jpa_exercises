package com.realdolmen.candyshop.dao;

import com.realdolmen.candyshop.domain.Person;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by vdabcursist on 08/08/2017.
 */
public class PersonDAOImpl implements PersonDAO {
    EntityManager em;

    @Override
    public Long createPerson(Person p) {
        em.persist(p);
        return p.getId();
    }

    @Override
    public List<Person> findAllPeople() {
        return em.createQuery("SELECT p FROM Person p").getResultList();
    }

    @Override
    public Person findPersonById(Long id) {
       return em.find(Person.class, id);
    }

    @Override
    public Person updatePerson(Person person) {
        return em.merge(person);
    }

    @Override
    public void deletePerson(Person person) {
        em.remove(em.merge(person));
    }

    @Override
    public long countAllPeople() {
       return em.createQuery("SELECT count(p) FROM Person p", Long.class).getSingleResult();
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return em.createNamedQuery("FIND_BY_LAST_NAME", Person.class).setParameter("lname", lastName).getResultList();
    }
}