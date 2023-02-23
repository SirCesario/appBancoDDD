package com.appbank.domain.values;

import com.appbank.business.generic.ValueObject;

public class Estado implements ValueObject<String> {

    private final String estado;

    public Estado(String estado){ this.estado = estado; }

    @Override
    public String value(){ return estado;}

}
