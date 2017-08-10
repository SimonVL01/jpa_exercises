package com.realdolmen.candyshop.domain;

import javax.persistence.Embeddable;

/**
 * Created by vdabcursist on 08/08/2017.
 */

@Embeddable
public class Address {
    private String street;
    private int number;
    private String city;
    private int postalCode;

    public Address(String street, int number, String city, int postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
