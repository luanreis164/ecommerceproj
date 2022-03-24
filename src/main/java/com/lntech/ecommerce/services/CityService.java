package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.City;
import com.lntech.ecommerce.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public List<City> findByCity(Integer stateId){
        return repo.findCities(stateId);
    }


}
