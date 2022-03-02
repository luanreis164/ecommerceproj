package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/categories")
public class CategorieController {

    @Autowired
    private CategorieService service;

    @GetMapping
    public List<Categorie> list(){
        return  service.listAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        Categorie obj = service.findOne(id);
        return  ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categorie obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Categorie obj,@PathVariable Integer id ){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();

    }


}
