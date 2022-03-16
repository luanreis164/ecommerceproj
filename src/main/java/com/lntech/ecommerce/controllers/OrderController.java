package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.domain.Order;
import com.lntech.ecommerce.dto.CategorieDTO;
import com.lntech.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> find(@PathVariable Integer id) {
        Order obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Order obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
