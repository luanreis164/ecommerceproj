package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.dto.CostumerDTO;
import com.lntech.ecommerce.repositories.CostumerRepository;
import com.lntech.ecommerce.services.exceptions.DataIntegrityException;
import com.lntech.ecommerce.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository repo;


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

    private void updateData(Costumer newObj, Costumer obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }


}
