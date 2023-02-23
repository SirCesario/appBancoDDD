package com.appbank.domain.events;

import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.values.ClienteId;
import com.appbank.domain.values.CuentaId;

public class CuentaEliminada extends DomainEvent {

    private final ClienteId clienteId;
    private final CuentaId cuentaId;

    public CuentaEliminada( ClienteId clienteId, CuentaId cuentaId) {
        super("Cuenta Eliminada Correctamente");
        this.clienteId = clienteId;
        this.cuentaId = cuentaId;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public CuentaId getCuentaId() {
        return cuentaId;
    }
}
