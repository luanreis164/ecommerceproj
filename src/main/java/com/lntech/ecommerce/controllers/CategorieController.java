package com.lntech.ecommerce.controllers;

import com.lntech.ecommerce.domain.Categorie;
import com.lntech.ecommerce.dto.CategorieDTO;
import com.lntech.ecommerce.services.CategorieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/categories")
public class CategorieController {

    @Autowired
    private CategorieService service;

    @ApiOperation(value = "Lista categorias")
    @GetMapping
    public ResponseEntity<List<CategorieDTO>> findAll(){
        List<Categorie> list = service.findAll();
        List<CategorieDTO> listDTO = list.stream().map(CategorieDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @ApiOperation(value = "Busca por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categorie> find(@PathVariable Integer id){
        Categorie obj = service.find(id);
        return  ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Insere categoria")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Categorie> insert(@Valid @RequestBody CategorieDTO objDto){
        Categorie obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Atualiza categoria")
    @PreAuthorize("hasAnyHole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Categorie> update(@Valid @RequestBody CategorieDTO objDto,@PathVariable Integer id ){
        Categorie obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();

    }

    @ApiOperation(value = "Remove categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
            @ApiResponse(code = 404, message = "Código inexistente") })
    @PreAuthorize("hasAnyHole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Retorna todas as categorias com paginação")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategorieDTO>> findPage(
            @RequestParam(value = "page",defaultValue = "0")
            Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")
            Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name")
            String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")
            String direction){

        Page<Categorie> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategorieDTO> listDTO = list.map(CategorieDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }

    @ApiOperation(value = "Insere foto de categoria")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> uploadCategoriePicture(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable Integer id){
        URI uri = service.uploadCategoriePicture(multipartFile,id);
        return ResponseEntity.created(uri).build();

    }

}
