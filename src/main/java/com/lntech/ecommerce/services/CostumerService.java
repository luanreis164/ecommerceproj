package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.repositories.CostumerRepository;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository repo;


    public Costumer findOne(Integer id){
        Optional<Costumer> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Cliente não encontrado!Id:" + id + ",Tipo: " + Costumer.class.getName()) );
    }

    public List<Costumer> listAll(){
        List<Costumer> obj = repo.findAll();
        return obj;
    }



}
