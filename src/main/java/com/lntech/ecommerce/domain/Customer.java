package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.lntech.ecommerce.domain.enums.Profile;
import com.lntech.ecommerce.domain.enums.TypeClient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;

    private String cpfOrCnpj;
    private Integer type;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Adress> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEPHONE")
    private Set<String> telephones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();


    public Customer() {
        addProfile(Profile.CUSTOMER);
    }

    public Customer(Integer id, String name, String email, String cpfOrCnpj, TypeClient type,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type == null) ? null : type.getCod();
        this.password = password;
        addProfile(Profile.CUSTOMER);
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

    @JsonSetter
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCod());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
