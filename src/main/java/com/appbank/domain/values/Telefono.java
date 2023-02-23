package com.appbank.domain.values;


import com.appbank.business.generic.ValueObject;

public class Telefono implements ValueObject<String> {

    private final String telefono;
    public Telefono(String telefono) { this.telefono = telefono; }

    @Override
    public String value() { return telefono; }
}
