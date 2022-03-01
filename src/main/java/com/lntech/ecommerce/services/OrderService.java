package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Order;
import com.lntech.ecommerce.repositories.OrderRepository;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;


    public Order findOne(Integer id){
        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Pedido não encontrado!Id:" + id + ",Tipo: " + Order.class.getName()) );
    }

    public List<Order> listAll(){
        List<Order> obj = repo.findAll();
        return obj;
    }



}
