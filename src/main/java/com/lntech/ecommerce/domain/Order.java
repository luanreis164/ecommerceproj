package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress deliveryAdress;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemOrdered> itens = new HashSet<>();


    public Order() {
    }

    public Order(Integer id, Date instant, Costumer costumer, Adress deliveryAdress) {
        this.id = id;
        this.instant = instant;
        this.costumer = costumer;
        this.deliveryAdress = deliveryAdress;
    }

    public double getTotal(){
        double sum = 0.0;
        for(ItemOrdered io : itens){
            sum = sum + io.getSubTotal();
        }
        return sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Adress getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(Adress deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public Set<ItemOrdered> getItens() {
        return itens;
    }

    public void setItens(Set<ItemOrdered> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
