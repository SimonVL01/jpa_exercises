package com.realdolmen.candyshop.dao;

import com.realdolmen.candyshop.domain.*;
import com.realdolmen.candyshop.util.DateBuilder;
import org.junit.*;
import org.mockito.Mock;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

/**
 * Created by vdabcursist on 08/08/2017.
 */

public class PersonDAOTest {

    private static EntityManagerFactory emf;
    private static PersonDAOImpl dao;
    private EntityManager em;
    private EntityTransaction tx;

    @BeforeClass
    public static void setup() {
       emf = Persistence.createEntityManagerFactory("test");
       dao = new PersonDAOImpl();

    }

    @Before
    public void init() {
        em = emf.createEntityManager();
        dao.em = em;
        tx = em.getTransaction();
        tx.begin();
    }

    @After
    public void destroy() {
        tx.rollback();
        em.close();
    }

    @AfterClass
    public static void cleanUp() {
        emf.close();
    }

    @Test
    public void shouldSavePerson() {
        Person p = new Person("Fifty", "Cent", DateBuilder.createDate("1998-05-05"), new Address("aciaciastraat", 48, "Lummen", 3560));

        Long id = dao.createPerson(p);
        assertNotNull(id);
        assertEquals(new Long(3L), id);
    }

    @Test
    public void shouldFindAllPeople() {
        List<Person> people = dao.findAllPeople();
        assertNotNull(people);
        assertNotNull(people.size());
        assertEquals(2, people.size());
    }

    @Test
    public void shouldFindPeopleById() {
        Person person = dao.findPersonById(1L);
        assertNotNull(person);
        assertEquals(new Long(1L), person.getId());
        assertEquals("John", person.getFirstName());
    }

    @Test
    public void shouldUpdatePerson() {
        Person person = dao.findPersonById(1L);
        assertNotNull(person);
        person.setFirstName("Eugene");
        person = dao.updatePerson(person);
        em.flush();
        em.clear();
        person = dao.findPersonById(1L);
        assertEquals("Eugene", person.getFirstName());
    }

    @Test
    public void shouldRemovePerson() {
        Person person = dao.findPersonById(1L);
        assertNotNull(person);
        em.remove(person);
        em.flush();
        em.clear();
        assertNull(dao.findPersonById(1L));
    }

    @Test
    public void shouldCountAllPeople() {
        Long count = dao.countAllPeople();
        em.flush();
        em.clear();
    }

    @Test
    public void shouldMakeOrder() {
        Person person = em.find(Person.class, 1L);
        assertNotNull(person);
        Order o = new Order();
        o.setDeliveryAddress(new Address("aciaciastraat", 48, "Lummen", 3560));
        o.setPerson(person);
        em.persist(o);
        person.addOrderToHistory(o);
        Candy c = new Candy("gummy", 4.5, CandyColor.RED);
        em.persist(c);
        OrderLine oL = new OrderLine();
        oL.setCandy(c);
        oL.setQuantity(40);
        em.persist(oL);
        o.addOrderline(oL);

        assertEquals(180.0, o.calculateTotal(),0.0000001);
        assertNotNull(person.getId());
        assertNotNull(o.getId());
        assertNotNull(c.getId());
        assertNotNull(oL.getId());
    }
}