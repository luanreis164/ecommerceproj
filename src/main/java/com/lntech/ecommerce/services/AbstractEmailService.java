package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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

    protected String htmlFromTemplateOrder(Order obj) {
        Context context = new Context();
        context.setVariable("order",obj);
        return templateEngine.process("email/confirmedOrder",context);

    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Order obj){
        try {
            MimeMessage mm = prepareMimeMessageFromOrder(obj);
            sendHtmlEmail(mm);
        }catch (MessagingException e ){
            sendOrderConfirmationEmail(obj);
        }

    }

    protected MimeMessage prepareMimeMessageFromOrder(Order obj) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm,true);
        mmh.setTo(obj.getCustomer().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Order confirmed! Cod: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateOrder(obj),true);
        return mm;
    }

}
