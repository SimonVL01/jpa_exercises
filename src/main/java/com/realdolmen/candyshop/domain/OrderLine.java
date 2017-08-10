package com.realdolmen.candyshop.domain;

import javax.persistence.*;

/**
 * Created by vdabcursist on 10/08/2017.
 */

@Entity
@Table (name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "candy_id")
    private Candy candy;

    public Long getId() {
        return id;
    }

    public double calculateSubTotal() {
        double price = candy.getPrice();
        quantity = quantity;
        return price * quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Candy getCandy() {
        return candy;
    }

    public void setCandy(Candy candy) {
        this.candy = candy;
    }

    public OrderLine(Long id, int quantity, Candy candy) {
        this.id = id;
        this.quantity = quantity;
        this.candy = candy;
    }

    public OrderLine() {
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", candy=" + candy +
                '}';
    }
}
