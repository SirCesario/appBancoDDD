package com.appbank.domain.events;


import com.appbank.domain.entitys.Cuenta;
import com.appbank.domain.values.*;
import com.appbank.business.generic.DomainEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteActualizado extends DomainEvent {
    private  ClienteId clienteId;
    private  Nombre nombre;
   private  Apellido apellido;
   private  Correo correo;
   private  Telefono telefono;



    public ClienteActualizado(ClienteId clienteId, Nombre nombre, Apellido apellido, Correo correo, Telefono telefono) {
        super("Cliente Actualizado");
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
