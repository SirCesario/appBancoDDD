package com.appbank.domain.values;


import com.appbank.business.generic.ValueObject;

public class FechaCreacionCuenta implements ValueObject<String> {

    private final String fechaCreacionCuenta;

    public FechaCreacionCuenta(String fechaCreacionCuenta){ this.fechaCreacionCuenta = fechaCreacionCuenta; }

    @Override
    public String value() { return fechaCreacionCuenta; }
}
