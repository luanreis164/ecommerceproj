package com.lntech.ecommerce.services.validation;

import com.lntech.ecommerce.controllers.exceptions.FieldMessage;
import com.lntech.ecommerce.domain.Costumer;
import com.lntech.ecommerce.domain.enums.TypeClient;
import com.lntech.ecommerce.dto.CostumerDTO;
import com.lntech.ecommerce.dto.NewCostumerDTO;
import com.lntech.ecommerce.repositories.CostumerRepository;
import com.lntech.ecommerce.services.validation.utils.BR;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CostumerUpdateValidator implements ConstraintValidator<CostumerUpdate, CostumerDTO> {

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public CostumerRepository repo;


    @Override
    public boolean isValid(CostumerDTO objDto, ConstraintValidatorContext context) {

        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        //Criando uma lista de possiveis erros;
        List<FieldMessage> list = new ArrayList<>();


        Costumer aux = repo.findByEmail(objDto.getEmail());

        if(aux != null && !aux.getId().equals(uriId)){
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
