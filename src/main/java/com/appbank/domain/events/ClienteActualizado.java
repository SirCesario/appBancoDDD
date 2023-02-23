package com.appbank.domain.events;


import com.appbank.domain.values.*;
import com.appbank.business.generic.DomainEvent;

import java.util.List;

public class ClienteActualizado extends DomainEvent {
    private final ClienteId clienteId;
    private final Nombre nombre;
   private final Apellido apellido;
   private final Correo correo;
   private final Telefono telefono;


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
