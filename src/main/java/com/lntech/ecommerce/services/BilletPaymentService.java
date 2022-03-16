package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletPaymentService {

    public void fillBilletPayment(BilletPayment payment, Date orderInstant){
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderInstant);
        cal.add(Calendar.DAY_OF_MONTH,7);
        payment.setDateExpirate(cal.getTime());
    }

}
