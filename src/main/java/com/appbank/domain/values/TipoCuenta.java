package com.appbank.domain.values;


import com.appbank.business.generic.ValueObject;

public class TipoCuenta implements ValueObject<String> {

    private final String tipoDeCuenta;
    public TipoCuenta(String tipoDeCuenta) { this.tipoDeCuenta = tipoDeCuenta; }

    @Override
    public String value() { return tipoDeCuenta; }
}
