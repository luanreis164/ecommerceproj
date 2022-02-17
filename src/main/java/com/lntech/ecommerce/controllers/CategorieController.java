package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Categorie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/categories")
public class CategorieController {

    @GetMapping
    public List<Categorie> list(){
        Categorie cat1 = new Categorie(1,"Computers");
        Categorie cat2 = new Categorie(2,"Eletronics");

        List<Categorie> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        return categories;

    }

}
