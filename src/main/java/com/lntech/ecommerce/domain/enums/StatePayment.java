package com.lntech.ecommerce.domain.enums;

public enum StatePayment {
    PENDING(1,"Pendente"),
    SETTLED(2,"Quitado"),
    CANCELED(3,"Cancelado");

    private int cod;
    private String description;

    private StatePayment(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static StatePayment toEnum(Integer cod){

        if(cod == null){
            return null;
        }
        for (StatePayment x : StatePayment.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido! Cod:" + cod);
    }


}
