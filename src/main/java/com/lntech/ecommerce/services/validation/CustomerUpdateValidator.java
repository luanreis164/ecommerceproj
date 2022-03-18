package com.lntech.ecommerce.services.validation;

import com.lntech.ecommerce.controllers.exceptions.FieldMessage;
import com.lntech.ecommerce.domain.Customer;
import com.lntech.ecommerce.dto.CustomerDTO;
import com.lntech.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public CustomerRepository repo;


    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {

        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        //Criando uma lista de possiveis erros;
        List<FieldMessage> list = new ArrayList<>();


        Customer aux = repo.findByEmail(objDto.getEmail());

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
