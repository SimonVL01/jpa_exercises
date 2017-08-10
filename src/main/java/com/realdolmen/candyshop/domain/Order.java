package com.realdolmen.candyshop.domain;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdabcursist on 10/08/2017.
 */

@Entity
@Table (name = "candy_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    @Embedded
    private Address deliveryAddress;

    public void addOrderline(OrderLine od) {
    orderLines.add(od);
    }

    public double calculateTotal() {
       int size = orderLines.size();
       double total = 0;
       for (OrderLine cur : orderLines) {
           total += cur.calculateSubTotal();
       }
        return total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Order(long id, Person person, List<OrderLine> orderLines, Address deliveryAddress) {
        this.id = id;
        this.person = person;
        this.orderLines = orderLines;
        this.deliveryAddress = deliveryAddress;
    }

    public Order() {
    }
}
