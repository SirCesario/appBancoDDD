package com.appbank.domain.values;


import com.appbank.business.generic.ValueObject;

public class Correo implements ValueObject<String> {
    private final String correo;
    public Correo(String correo){ this.correo = correo; }

    @Override
    public String value(){ return correo;}
}
