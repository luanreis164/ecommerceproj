package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.domain.Product;
import com.lntech.ecommerce.repositories.CategorieRepository;
import com.lntech.ecommerce.repositories.ProductRepository;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategorieRepository categorieRepository;

    public Product find(Integer id){
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Pedido não encontrado!Id:" + id + ",Tipo: " + Product.class.getName()) );
    }

    public Page<Product> search(String name,List<Integer> ids,Integer page,Integer linesPerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categorie> categories = categorieRepository.findAllById(ids);
        return repo.findDistinctByNameContainingAndCategoriesIn(name,categories,pageRequest);


    }


}
