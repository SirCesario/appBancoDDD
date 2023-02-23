package com.appbank.domain.values;

import com.appbank.business.generic.ValueObject;

public class ValorTransaccion implements ValueObject<String> {

    private String valorTransaccion;

    public ValorTransaccion(String valorTransaccion){ this.valorTransaccion = valorTransaccion; }

    @Override
    public String value(){ return valorTransaccion;}

}
