package com.appbank.domain.entitys;

import com.appbank.business.generic.Entity;
import com.appbank.domain.values.*;


import java.util.List;
import java.util.Set;

public class Cuenta extends Entity<CuentaId> {

    private ClienteId clienteId;
    private FechaCreacionCuenta fechaCreacionCuenta;
    private TipoCuenta tipoCuenta;
    private Saldo saldo;

    protected Set<Transaccion> transacciones;



    public Cuenta(CuentaId entityId,ClienteId clienteId, FechaCreacionCuenta fechaCreacionCuenta, TipoCuenta tipoCuenta, Saldo saldo, Set<Transaccion> transacciones){
        super(entityId);
        this.clienteId = clienteId;
        this.fechaCreacionCuenta = fechaCreacionCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.transacciones = transacciones;
    }

    public FechaCreacionCuenta getFechaCreacionCuenta() {
        return fechaCreacionCuenta;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public Set<Transaccion> getTransacciones() {
        return transacciones;
    }
}
