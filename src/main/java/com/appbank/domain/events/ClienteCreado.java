package com.appbank.domain.events;


import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.entitys.Cuenta;
import com.appbank.domain.values.*;

import java.util.Set;


public class ClienteCreado extends DomainEvent {


    private final ClienteId clienteId;
    private final Nombre nombre;
    private final Apellido apellido;
    private final Correo correo;
    private final Telefono telefono;



    public ClienteCreado(ClienteId clienteId, Nombre nombre, Apellido apellido, Correo correo, Telefono telefono) {
        super(ClienteCreado.class.getName());
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;

    }


    public ClienteId getClienteId() {
        return clienteId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Apellido getApellido() {
        return apellido;
    }

    public Correo getCorreo() {
        return correo;
    }

    public Telefono getTelefono() {
        return telefono;
    }


}
