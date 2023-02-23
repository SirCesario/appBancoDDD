package com.appbank.domain;


import com.appbank.business.generic.DomainEvent;
import com.appbank.business.generic.EventChange;
import com.appbank.domain.entitys.Cuenta;

import com.appbank.domain.entitys.Transaccion;
import com.appbank.domain.events.ClienteActualizado;
import com.appbank.domain.events.ClienteCreado;
import com.appbank.domain.events.CuentaAgregada;
import com.appbank.domain.events.CuentaEliminada;
import com.appbank.domain.values.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteEventChange extends EventChange {

    public ClienteEventChange(Cliente cliente){
        apply((ClienteCreado event)->{
            cliente.nombre = event.getNombre();
            cliente.apellido = event.getApellido();
            cliente.correo = event.getCorreo();
            cliente.telefono = event.getTelefono();
            cliente.cuentas = new HashSet<>();
        });

        apply((CuentaAgregada event)->{
            Cuenta cuenta = new Cuenta(event.getCuentaId(),event.getClienteId(),event.getFechaCreacionCuenta(),
                    event.getTipoCuenta(),event.getSaldo(),new HashSet<Transaccion>());
            cliente.cuentas.add(cuenta);
        });

        apply((ClienteActualizado event) -> {
            ClienteId clienteId = ClienteId.of(event.getClienteId().toString());
            Nombre nombre = new Nombre(event.getNombre().toString());
            Apellido apellido = new Apellido(event.getApellido().toString());
            Correo correo = new Correo(event.getCorreo().toString());
            Telefono telefono = new Telefono(event.getTelefono().toString());
            cliente.actualizarCliente(clienteId, nombre, apellido, correo, telefono);
        });

        apply((CuentaEliminada event)->{
            cliente.quitarCuentaCliente(event.getClienteId(),event.getCuentaId());
            cliente.cuentas.remove(event.getCuentaId());
        });
    }


}
