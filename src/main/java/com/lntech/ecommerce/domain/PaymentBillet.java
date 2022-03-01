package com.lntech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lntech.ecommerce.domain.enums.StatePayment;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentBillet extends Payment{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateExpirate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datePayment;

    public PaymentBillet() {
    }

    public PaymentBillet(Integer id, StatePayment statePayment, Order order, Date dateExpirate, Date datePayment) {
        super(id, statePayment, order);
        this.dateExpirate = dateExpirate;
        this.datePayment = datePayment;
    }

    public Date getDateExpirate() {
        return dateExpirate;
    }

    public void setDateExpirate(Date dateExpirate) {
        this.dateExpirate = dateExpirate;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

}
