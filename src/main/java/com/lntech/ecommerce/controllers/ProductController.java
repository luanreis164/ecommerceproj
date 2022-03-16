package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.controllers.utils.URL;
import com.lntech.ecommerce.domain.Product;
import com.lntech.ecommerce.dto.ProductDTO;
import com.lntech.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name",defaultValue = "") String name,
            @RequestParam(value = "categories",defaultValue = "")String categories,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name")String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")String direction){
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nameDecoded,ids,page,linesPerPage,orderBy,direction);
        Page<ProductDTO> listDTO = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }


}
