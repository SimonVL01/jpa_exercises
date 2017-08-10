package com.realdolmen.candyshop.dao;

import com.realdolmen.candyshop.domain.Person;

import java.util.List;

/**
 * Created by vdabcursist on 08/08/2017.
 */
public interface PersonDAO {

    Long createPerson(Person p);
    List<Person> findAllPeople();
    Person findPersonById(Long id);
    Person updatePerson(Person person);
    void deletePerson(Person person);
    long countAllPeople();
    List<Person> findByLastName(String lastName);

}
