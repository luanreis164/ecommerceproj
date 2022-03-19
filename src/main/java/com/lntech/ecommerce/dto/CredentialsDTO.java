package com.lntech.ecommerce.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable{

    private String email;
    private String password;

    public CredentialsDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
