package com.appbank.domain;


import com.appbank.business.generic.AggregateRoot;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.entitys.Cuenta;
import com.appbank.domain.events.ClienteActualizado;
import com.appbank.domain.events.ClienteCreado;
import com.appbank.domain.events.CuentaAgregada;
import com.appbank.domain.events.CuentaEliminada;
import com.appbank.domain.values.*;

import java.util.*;

public class Cliente extends AggregateRoot<ClienteId> {


    protected Nombre nombre;
    protected Apellido apellido;
    protected Correo correo;
    protected Telefono telefono;
    protected Set<Cuenta> cuentas;

    public Cliente(ClienteId id, Nombre nombre,Apellido apellido,Correo correo,Telefono telefono){
        super(id);
        subscribe(new ClienteEventChange(this));
        appendChange(new ClienteCreado(id,nombre,apellido,correo,telefono)).apply();
    }

    public static Cliente from(ClienteId clienteId, List<DomainEvent>events){
        Cliente cliente = new Cliente(clienteId);
        events.forEach(cliente::applyEvent);
        return cliente;
    }

    public Cliente(ClienteId id){
        super(id);
        subscribe(new ClienteEventChange(this));
    }

    public void agregarCuenta(CuentaId id, ClienteId clienteId, FechaCreacionCuenta fechaCreacionCuenta, TipoCuenta tipoCuenta,Saldo saldo){
        Objects.requireNonNull(id);
        Objects.requireNonNull(clienteId);
        Objects.requireNonNull(fechaCreacionCuenta);
        Objects.requireNonNull(tipoCuenta);
        Objects.requireNonNull(saldo);
        appendChange(new CuentaAgregada(id,clienteId,fechaCreacionCuenta,tipoCuenta,saldo)).apply();
    }

    public void actualizarCliente(ClienteId id,Nombre nombre,Apellido apellido,Correo correo,Telefono telefono){
        Objects.requireNonNull(id);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(apellido);
        Objects.requireNonNull(correo);
        Objects.requireNonNull(telefono);
        appendChange(new ClienteActualizado(id,nombre,apellido,correo,telefono)).apply();
    }

    public void quitarCuentaCliente(ClienteId id,CuentaId cuentaId){
        Objects.requireNonNull(id);
        Objects.requireNonNull(cuentaId);
        appendChange(new CuentaEliminada(id,cuentaId)).apply();
    }

}

