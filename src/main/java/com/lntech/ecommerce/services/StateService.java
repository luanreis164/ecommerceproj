package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.State;
import com.lntech.ecommerce.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository repo;

    public List<State> findAll(){
        return repo.findAllByOrderByName();
    }


}
