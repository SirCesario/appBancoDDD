package com.appbank.domain.values;


import com.appbank.business.generic.ValueObject;

public class Saldo implements ValueObject<String> {

    private final String saldoCuenta;
    public Saldo(String saldoCuenta) { this.saldoCuenta = saldoCuenta; }

    @Override
    public String value() { return saldoCuenta; }
}
