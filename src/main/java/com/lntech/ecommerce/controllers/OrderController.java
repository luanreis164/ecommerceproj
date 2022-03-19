package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Order;
import com.lntech.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public ResponseEntity<Page<Order>> findPage(
            @RequestParam(value = "page",defaultValue = "0")
                    Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")
                    Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "instant")
                    String orderBy,
            @RequestParam(value = "direction",defaultValue = "DESC")
                    String direction){

        Page<Order> list = service.findPage(page,linesPerPage,orderBy,direction);
        return ResponseEntity.ok().body(list);
    }

}
