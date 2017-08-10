package com.realdolmen.candyshop.main;

import com.realdolmen.candyshop.domain.*;
import com.realdolmen.candyshop.util.DateBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vdabcursist on 08/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("candyshop");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        //Person person = new Person("Fifty", "Cent", DateBuilder.createDate("1998-05-05"), new Address("aciaciastraat", 48, "Lummen", 3560));

        //person.addCandyPreference(CandyColor.RED);


        Person person1 = new Person("Fifty", "Cent", DateBuilder.createDate("1998-05-05"), new Address("aciaciastraat", 48, "Lummen", 3560));
        em.persist(person1);
        Order o = new Order();
        o.setDeliveryAddress(new Address("aciaciastraat", 48, "Lummen", 3560));
        o.setPerson(person1);
        em.persist(o);
        person1.addOrderToHistory(o);
        Candy c = new Candy("gummy", 4.5, CandyColor.RED);
        em.persist(c);
        OrderLine oL = new OrderLine();
        o.addOrderline(oL);
        oL.setCandy(c);
        oL.setQuantity(40);
        o.addOrderline(oL);
        em.persist(oL);

        tx.commit();

        List<Person> people = em.createQuery("SELECT p from Person p", Person.class).getResultList();
        people.forEach(System.out::println);

        em.close();
        emf.close();

    }
}