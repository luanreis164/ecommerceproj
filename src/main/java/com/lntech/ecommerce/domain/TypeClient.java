package com.lntech.ecommerce.domain;

public enum TypeClient {

    NATURALPERSON(1,"Pessoa Física"),
    LEGALPERSON(2,"Pessoa Jurídica");

    private int cod;
    private String description;

    private TypeClient(int cod,String description){
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TypeClient toEnum(Integer cod){

        if(cod == null){
            return null;
        }
        for (TypeClient x : TypeClient.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido! Cod:" + cod);
    }


}
