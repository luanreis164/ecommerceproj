package com.lntech.ecommerce.dto;

import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.services.validation.CustomerUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@CustomerUpdate
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 3,max = 120,message = "O tamanho deve ser entre 3 e 120 caracteres!")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer){
        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
