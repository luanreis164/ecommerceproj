package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.dto.CustomerDTO;
import com.lntech.ecommerce.dto.NewCustomerDTO;
import com.lntech.ecommerce.services.CustomerService;
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
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> find(@PathVariable Integer id){
        Customer obj = service.find(id);
        return  ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<Customer> list = service.findAll();
        List<CustomerDTO> listDTO = list.stream().map(CustomerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@Valid @RequestBody CustomerDTO objDto, @PathVariable Integer id ){
        Customer obj = service.fromDTO(objDto);
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
    public ResponseEntity<Page<CustomerDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0")
                    Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")
                    Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name")
                    String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")
                    String direction){

        Page<Customer> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CustomerDTO> listDTO = list.map(CustomerDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NewCustomerDTO objDto){
        Customer obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
