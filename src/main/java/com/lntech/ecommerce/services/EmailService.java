package com.lntech.ecommerce.services;


import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.domain.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Customer customer,String newPass);

}
