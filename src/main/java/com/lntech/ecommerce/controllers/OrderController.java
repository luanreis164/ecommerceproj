package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Order;
import com.lntech.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> find(@PathVariable Integer id) {
        Order obj = service.findOne(id);
        return ResponseEntity.ok().body(obj);
    }


}
