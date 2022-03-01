package com.lntech.ecommerce.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemOrdered implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemOrderedPK id = new ItemOrderedPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public ItemOrdered() {
    }

    public ItemOrdered(Order order,Product product, Double discount, Integer amount, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public ItemOrderedPK getId() {
        return id;
    }

    public void setId(ItemOrderedPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrdered that = (ItemOrdered) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
