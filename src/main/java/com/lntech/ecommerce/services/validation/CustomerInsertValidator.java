package com.lntech.ecommerce.services.validation;

import com.lntech.ecommerce.controllers.exceptions.FieldMessage;
import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.domain.enums.TypeClient;
import com.lntech.ecommerce.dto.NewCustomerDTO;
import com.lntech.ecommerce.repositories.CustomerRepository;
import com.lntech.ecommerce.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> {

    @Autowired
    public CustomerRepository repo;


    @Override
    public void initialize(CustomerInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(NewCustomerDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(TypeClient.NATURALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj","CPF inválido!"));
        }
        if (objDto.getType().equals(TypeClient.LEGALPERSON.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj","CNPJ inválido!"));
        }

        Customer aux = repo.findByEmail(objDto.getEmail());

        if(aux != null){
            list.add(new FieldMessage("email","Email ja cadastrado!"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
