package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.dto.CategorieDTO;
import com.lntech.ecommerce.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/categories")
public class CategorieController {

    @Autowired
    private CategorieService service;

    @GetMapping
    public ResponseEntity<List<CategorieDTO>> findAll(){
        List<Categorie> list = service.findAll();
        List<CategorieDTO> listDTO = list.stream().map(CategorieDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categorie> find(@PathVariable Integer id){
        Categorie obj = service.find(id);
        return  ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<List<Categorie>> insert(@RequestBody Categorie obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categorie> update(@RequestBody Categorie obj,@PathVariable Integer id ){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
