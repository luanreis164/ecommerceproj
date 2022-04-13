package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.dto.CustomerDTO;
import com.lntech.ecommerce.dto.NewCustomerDTO;
import com.lntech.ecommerce.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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


    @ApiOperation(value = "Busca cliente por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> find(@PathVariable Integer id){
        Customer obj = service.find(id);
        return  ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Busca de cliente por email")
    @GetMapping(value = "/email")
    public ResponseEntity<Customer> findByEmail(@RequestParam(value = "value")String email){
        Customer obj = service.findByEmail(email);
        return  ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Retorna todos os clientes")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        List<Customer> list = service.findAll();
        List<CustomerDTO> listDTO = list.stream().map(CustomerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @ApiOperation(value = "Atualiza um cliente")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@Valid @RequestBody CustomerDTO objDto, @PathVariable Integer id ){
        Customer obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();

    }

    @ApiOperation(value = "Remover cliente")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Retorna todas os clientes com paginação")
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

    @ApiOperation(value = "Insere um cliente")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NewCustomerDTO objDto){
        Customer obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Adicionar foto de perfil")
    @PostMapping(value = "/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name ="file") MultipartFile multipartFile){
       URI uri = service.uploadProfilePicture(multipartFile);
        return ResponseEntity.created(uri).build();
    }

}
