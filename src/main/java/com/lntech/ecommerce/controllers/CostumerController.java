package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.dto.CategorieDTO;
import com.lntech.ecommerce.dto.CostumerDTO;
import com.lntech.ecommerce.dto.NewCostumerDTO;
import com.lntech.ecommerce.services.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/costumers")
public class CostumerController {

    @Autowired
    private CostumerService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Costumer> find(@PathVariable Integer id){
        Costumer obj = service.find(id);
        return  ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CostumerDTO>> findAll(){
        List<Costumer> list = service.findAll();
        List<CostumerDTO> listDTO = list.stream().map(CostumerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Costumer> update(@Valid @RequestBody CostumerDTO objDto, @PathVariable Integer id ){
        Costumer obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CostumerDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0")
                    Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")
                    Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name")
                    String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")
                    String direction){

        Page<Costumer> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CostumerDTO> listDTO = list.map(CostumerDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NewCostumerDTO objDto){
        Costumer obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
