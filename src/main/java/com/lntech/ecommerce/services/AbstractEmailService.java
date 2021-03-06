package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.domain.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Order obj){
        SimpleMailMessage sm = prepareSimpleEmailMessageFromOrder(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleEmailMessageFromOrder(Order obj){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCustomer().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Confirmed order! cod: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Customer customer,String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail(customer,newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(customer.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Recover password! ");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("New password: " + newPass);
        return sm;

    }

}
