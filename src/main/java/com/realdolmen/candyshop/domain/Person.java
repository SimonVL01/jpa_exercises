package com.realdolmen.candyshop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vdabcursist on 08/08/2017.
 */
@Entity
@Table(name = "person")
@NamedQuery(name = "FIND_BY_LAST_NAME", query = "SELECT p FROM Person p WHERE p.lastName = :lname")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false, length = 200)
    private String firstName;
    @NotNull
    @Column(nullable = false, length = 200)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Transient
    private Integer age;
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "candyPreferences")
    private List<CandyColor> candyPreferences = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Order> orderHistory = new ArrayList<Order>();

    public void addOrderToHistory(Order o) {
        orderHistory.add(o);
    }

    public Person(String firstName, String lastName, Date birthDate,
                  Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<CandyColor> getCandyPreferences() {
        return candyPreferences;
    }

    public void setCandyPreferences(List<CandyColor> candyPreferences) {
        this.candyPreferences = candyPreferences;
    }

    public void addCandyPreference(CandyColor color) {
        candyPreferences.add(color);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                '}';
    }
}
