package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.City;
import com.lntech.ecommerce.domain.State;
import com.lntech.ecommerce.dto.CityDTO;
import com.lntech.ecommerce.dto.StateDTO;
import com.lntech.ecommerce.services.CityService;
import com.lntech.ecommerce.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateController {

    @Autowired
    private StateService service;

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll(){
        List<State> states = service.findAll();
        List<StateDTO> listDto = states.stream().map(StateDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{stateId}/cities" )
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){
        List<City> cities = cityService.findByCity(stateId);
        List<CityDTO> listDto = cities.stream().map(CityDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


}
