package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity(name = "Orders")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Adress deliveryAdress;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemOrdered> items = new HashSet<>();


    public Order() {
    }

    public Order(Integer id, Date instant, Customer customer, Adress deliveryAdress) {
        this.id = id;
        this.instant = instant;
        this.customer = customer;
        this.deliveryAdress = deliveryAdress;
    }

    public double getTotal(){
        double sum = 0.0;
        for(ItemOrdered io : items){
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Adress getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(Adress deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public Set<ItemOrdered> getItems() {
        return items;
    }

    public void setItems(Set<ItemOrdered> items) {
        this.items = items;
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

    @Override
    public String toString() {

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("Order number: ");
        builder.append(getId());
        builder.append(", Instant: ");
        builder.append(sdf.format(getInstant()));
        builder.append(", Customer: ");
        builder.append(getCustomer().getName());
        builder.append(", Payment state: ");
        builder.append(getPayment().getStatePayment().getDescription());
        builder.append("\nDetails:\n");
        for(ItemOrdered ip : getItems()){
            builder.append(ip.toString());
        }
        builder.append("Total: ");
        builder.append(nf.format(getTotal()));
        return builder.toString();
    }
}
