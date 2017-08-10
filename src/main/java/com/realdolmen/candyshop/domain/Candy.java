package com.realdolmen.candyshop.domain;

import javax.persistence.*;

/**
 * Created by vdabcursist on 08/08/2017.
 */

//@Entity
@Entity
public class Candy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private double price;

    @Enumerated(EnumType.STRING)
    private CandyColor candyColor;

    public Candy(String name, double price, CandyColor candyColor) {
        this.name = name;
        this.price = price;
        this.candyColor = candyColor;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CandyColor getCandyColor() {
        return candyColor;
    }

    public void setCandyColor(CandyColor candyColor) {
        this.candyColor = candyColor;
    }

    public Candy() {


    }
}
