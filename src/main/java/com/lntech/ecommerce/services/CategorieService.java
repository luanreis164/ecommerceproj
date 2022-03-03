package com.lntech.ecommerce.services;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.repositories.CategorieRepository;
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
public class CategorieService {

    @Autowired
    private CategorieRepository repo;


    public Categorie find(Integer id){
        Optional<Categorie> obj = repo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Categoria não encontrada!Id:" + id + ",Tipo: " + Categorie.class.getName()) );
    }

    public List<Categorie> findAll(){
        return repo.findAll();
    }

    public Categorie insert(Categorie obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Categorie update(Categorie obj){
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try{
        repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos! ");
        }

    }

    public Page<Categorie> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }



}
