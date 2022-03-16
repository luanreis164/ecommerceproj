package com.lntech.ecommerce.services.validation;

import com.lntech.ecommerce.controllers.exceptions.FieldMessage;
import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.domain.enums.TypeClient;
import com.lntech.ecommerce.dto.NewCostumerDTO;
import com.lntech.ecommerce.repositories.CostumerRepository;
import com.lntech.ecommerce.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CostumerInsertValidator implements ConstraintValidator<CostumerInsert, NewCostumerDTO> {

    @Autowired
    public CostumerRepository repo;


    @Override
    public void initialize(CostumerInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(NewCostumerDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(TypeClient.NATURALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj","CPF inválido!"));
        }
        if (objDto.getType().equals(TypeClient.LEGALPERSON.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj","CNPJ inválido!"));
        }

        Costumer aux = repo.findByEmail(objDto.getEmail());

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
