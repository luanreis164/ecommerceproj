package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lntech.ecommerce.domain.enums.StatePayment;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class CardPayment extends Payment{

    private Integer numberInstallments;

    public CardPayment() {
    }

    public CardPayment(Integer id, StatePayment statePayment, Order order, Integer numberInstallments) {
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
