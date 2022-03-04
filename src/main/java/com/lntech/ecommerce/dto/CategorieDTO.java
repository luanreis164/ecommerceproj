package com.lntech.ecommerce.domain.dto;

import com.lntech.ecommerce.domain.Categorie;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategorieDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3,max = 80,message = "O tamanho deve ter entre 3 e 80 caracteres.")
    private String name;

    public CategorieDTO() {
    }

    public CategorieDTO(Categorie obj){
        id = obj.getId();
        name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
