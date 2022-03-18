package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Adress;
import com.lntech.ecommerce.domain.ItemOrdered;
import com.lntech.ecommerce.domain.Order;
import com.lntech.ecommerce.domain.BilletPayment;
import com.lntech.ecommerce.domain.enums.StatePayment;
import com.lntech.ecommerce.repositories.ItemOrderedRepository;
import com.lntech.ecommerce.repositories.OrderRepository;
import com.lntech.ecommerce.repositories.PaymentRepository;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private ItemOrderedRepository itemOrderedRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private BilletPaymentService billetPaymentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;


    public Order find(Integer id){
        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Pedido não encontrado!Id:" + id + ",Tipo: " + Order.class.getName()) );
    }

    @Transactional
    public Order insert(Order obj){
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setCustomer(customerService.find(obj.getCustomer().getId()));
        obj.getPayment().setStatePayment(StatePayment.PENDING);
        obj.getPayment().setOrder(obj);
        if(obj.getPayment() instanceof BilletPayment){
            BilletPayment payment = (BilletPayment) obj.getPayment();
            billetPaymentService.fillBilletPayment(payment,obj.getInstant());
        }
        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for(ItemOrdered io : obj.getItens()){
            io.setDiscount(0.0);
            io.setProduct(productService.find(io.getProduct().getId()));
            io.setPrice(io.getProduct().getPrice());
            io.setOrder(obj);
        }
        itemOrderedRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);

        return obj;
    }



}
