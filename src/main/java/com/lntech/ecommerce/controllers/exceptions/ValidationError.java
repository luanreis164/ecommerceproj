package com.lntech.ecommerce.controllers.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getList() {
        return errors;
    }

    public void addError(String fieldMessage,String message){
        errors.add(new FieldMessage(fieldMessage,message));
    }

}
