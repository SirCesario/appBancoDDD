package com.appbank.domain;


import com.appbank.business.generic.EventChange;
import com.appbank.domain.entitys.Cuenta;

import com.appbank.domain.entitys.Transaccion;
import com.appbank.domain.events.ClienteCreado;
import com.appbank.domain.events.CuentaAgregada;
import com.appbank.domain.values.ClienteId;
import com.appbank.domain.values.FechaCreacionCuenta;
import com.appbank.domain.values.Saldo;
import com.appbank.domain.values.TipoCuenta;

import java.util.ArrayList;
import java.util.HashSet;
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
    }


}
