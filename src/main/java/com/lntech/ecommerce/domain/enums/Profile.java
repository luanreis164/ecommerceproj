package com.lntech.ecommerce.domain.enums;

public enum Profile {
    ADMIN(1,"ROLE_ADMIN"),
    CUSTOMER(2,"ROLE_CUSTOMER");


    private int cod;
    private String description;

    private Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer cod){

        if(cod == null){
            return null;
        }
        for (Profile x : Profile.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido! Cod:" + cod);
    }


}
