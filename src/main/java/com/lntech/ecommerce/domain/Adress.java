package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Adress implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name ="costumer_id")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name ="city_id")
    private City city;

    public Adress() {
    }

    public Adress(Integer id, String address, String number, String complement, String neighborhood, String postalCode, Costumer costumer, City city) {
        this.id = id;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.postalCode = postalCode;
        this.costumer = costumer;
        this.setCity(city);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonIgnore
    public Costumer getCustomer() {
        return costumer;
    }

    public void setCustomer(Costumer costumer) {
        this.costumer = costumer;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(id, adress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
