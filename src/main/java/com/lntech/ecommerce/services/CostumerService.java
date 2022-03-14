package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Adress;
import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.domain.City;
import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.domain.enums.TypeClient;
import com.lntech.ecommerce.dto.CategorieDTO;
import com.lntech.ecommerce.dto.CostumerDTO;
import com.lntech.ecommerce.dto.NewCostumerDTO;
import com.lntech.ecommerce.repositories.AdressRepository;
import com.lntech.ecommerce.repositories.CostumerRepository;
import com.lntech.ecommerce.services.exceptions.DataIntegrityException;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository repo;

    @Autowired
    private AdressRepository adressRepository;


    public Costumer find(Integer id){
        Optional<Costumer> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Cliente não encontrado!Id:" + id + ",Tipo: " + Costumer.class.getName()) );
    }

    public List<Costumer> findAll(){
        List<Costumer> obj = repo.findAll();
        return obj;
    }
    public Costumer update(Costumer obj){
        Costumer newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível porque há entidades relacionadas! ");
        }

    }

    public Page<Costumer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Costumer fromDTO(CostumerDTO objDto){
        return new Costumer(objDto.getId(), objDto.getName(), objDto.getEmail(),null,null);
    }

    public Costumer fromDTO(NewCostumerDTO objDto){
        Costumer costumer = new Costumer(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), TypeClient.toEnum(objDto.getType()));
        City city = new City(objDto.getCityId(), null,null);
        Adress adress = new Adress(null,objDto.getAddress(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getPostalCode(),costumer,city);

        costumer.getAdresses().add(adress);
        costumer.getTelephones().add(objDto.getTelephone1());
        if(objDto.getTelephone2() != null){
            costumer.getTelephones().add(objDto.getTelephone2());
        }
        if (objDto.getTelephone3() != null){
            costumer.getTelephones().add(objDto.getTelephone3());
        }
        return costumer;
    }


    private void updateData(Costumer newObj, Costumer obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    @Transactional
    public Costumer insert(Costumer obj){
    obj.setId(null);
    obj = repo.save(obj);
    adressRepository.saveAll(obj.getAdresses());
    return obj;
    }



}
