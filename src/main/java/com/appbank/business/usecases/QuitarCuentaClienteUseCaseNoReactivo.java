package com.appbank.business.usecases;

import com.appbank.business.gateways.RepositoryExample;
import com.appbank.business.generic.DomainEvent;
import com.appbank.business.generic.UseCaseForCommandNoReactivo;
import com.appbank.domain.Cliente;
import com.appbank.domain.command.QuitarCuentaClienteCommand;
import com.appbank.domain.values.ClienteId;
import com.appbank.domain.values.CuentaId;

import java.util.List;
import java.util.stream.Collectors;

public class QuitarCuentaClienteUseCaseNoReactivo extends UseCaseForCommandNoReactivo<QuitarCuentaClienteCommand> {
    private final RepositoryExample repository;

    public QuitarCuentaClienteUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(QuitarCuentaClienteCommand command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getClienteId());
        Cliente cliente = Cliente.from(ClienteId.of(command.getClienteId()), events);
        cliente.quitarCuentaCliente(ClienteId.of(command.getClienteId()),CuentaId.of(command.getCuentaId()));
        return cliente.getUncommittedChanges().stream().map(event -> {
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
