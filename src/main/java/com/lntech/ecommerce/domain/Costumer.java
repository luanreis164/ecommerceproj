package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lntech.ecommerce.domain.enums.TypeClient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Costumer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String cpfOrCnpj;

    private Integer type;

    @OneToMany(mappedBy = "costumer")
    private List<Adress> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEPHONE")
    private Set<String> telephones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "costumer")
    private List<Order> orders = new ArrayList<>();

    public Costumer() {
    }

    public Costumer(Integer id, String name, String email, String cpfOrCnpj, TypeClient type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type == null) ? null : type.getCod();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public TypeClient getType() {
        return TypeClient.toEnum(type);
    }

    public void setType(TypeClient type) {
        this.type = type.getCod();
    }

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public Set<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(Set<String> telephones) {
        this.telephones = telephones;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costumer costumer = (Costumer) o;
        return Objects.equals(id, costumer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
