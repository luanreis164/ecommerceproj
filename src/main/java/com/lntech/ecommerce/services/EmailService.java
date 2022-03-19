package com.lntech.ecommerce.services;


import com.lntech.ecommerce.domain.Order;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order obj);

    void sendOrderConfirmationHtmlEmail(Order obj);

    void sendEmail(SimpleMailMessage msg);

    void sendHtmlEmail(MimeMessage msg);
}
