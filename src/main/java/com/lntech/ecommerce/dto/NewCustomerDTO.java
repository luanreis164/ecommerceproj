package com.lntech.ecommerce.dto;


import com.lntech.ecommerce.services.validation.CustomerInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@CustomerInsert
public class NewCustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 3,max = 80,message = "O tamanho deve ser entre 3 e 80!")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cpfOrCnpj;

    private Integer type;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 3,max = 80,message = "O tamanho deve ser entre 3 e 80 caracteres!")
    private String address;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 1,max = 11,message = "O tamanho deve ser entre 1 e 11 caracteres!")
    private String number;

    private String complement;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 3,max = 80,message = "O tamanho deve ser entre 3 e 80 caracteres!")
    private String neighborhood;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 7,max = 10,message = "CEP inválido!")
    private String postalCode;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Size(min = 11,max = 17,message = "O telefone deve ter o formato correto.")
    private String telephone1;

    private String telephone2;
    private String telephone3;

    private Integer cityId;

    public NewCustomerDTO() {
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
