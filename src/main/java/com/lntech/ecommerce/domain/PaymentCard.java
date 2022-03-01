package com.lntech.ecommerce.domain;

import com.lntech.ecommerce.domain.enums.StatePayment;

import javax.persistence.Entity;

@Entity
public class PaymentCard extends Payment{

    private Integer numberInstallments;

    public PaymentCard() {
    }

    public PaymentCard(Integer id, StatePayment statePayment, Order order, Integer numberInstallments) {
        super(id, statePayment, order);
        this.numberInstallments = numberInstallments;
    }

    public Integer getNumberInstallments() {
        return numberInstallments;
    }

    public void setNumberInstallments(Integer numberInstallments) {
        this.numberInstallments = numberInstallments;
    }


}
