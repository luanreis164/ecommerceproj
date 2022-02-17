package com.lntech.ecommerce.domain;


import java.io.Serializable;
import java.util.Objects;

public class Categorie implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Categorie() {
    }

    public Categorie(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return Objects.equals(id, categorie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
