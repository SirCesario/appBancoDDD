package com.appbank.domain.values;


import com.appbank.business.generic.ValueObject;

public class Apellido implements ValueObject<String> {

    private final String apellido;
    public Apellido(String apellido) {this.apellido = apellido;}

    @Override
    public String value(){ return apellido;}
}
