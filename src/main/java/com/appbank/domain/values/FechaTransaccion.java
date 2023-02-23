package com.appbank.domain.values;

import com.appbank.business.generic.ValueObject;

public class FechaTransaccion implements ValueObject<String> {

    private String fechaTransaccion;

    public FechaTransaccion(String fechaTransaccion){ this.fechaTransaccion = fechaTransaccion ;}

    @Override
    public String value(){ return fechaTransaccion; }
}
