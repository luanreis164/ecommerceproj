package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.services.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/costumers")
public class CostumerController {

    @Autowired
    private CostumerService service;

    @GetMapping
    public List<Costumer> list(){
        return  service.listAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        Costumer obj = service.findOne(id);
        return  ResponseEntity.ok().body(obj);
    }



}
