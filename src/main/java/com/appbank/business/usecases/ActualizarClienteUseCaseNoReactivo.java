package com.appbank.business.usecases;

import com.appbank.business.gateways.RepositoryExample;
import com.appbank.business.generic.DomainEvent;
import com.appbank.business.generic.UseCaseForCommandNoReactivo;
import com.appbank.domain.Cliente;
import com.appbank.domain.command.ActualizarClienteCommand;
import com.appbank.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class ActualizarClienteUseCaseNoReactivo extends UseCaseForCommandNoReactivo <ActualizarClienteCommand> {


    private RepositoryExample repository;

    public ActualizarClienteUseCaseNoReactivo(RepositoryExample repository){ this.repository = repository; }

    @Override
    public List<DomainEvent> apply(ActualizarClienteCommand command){
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getClienteId());
        Cliente cliente = Cliente.from(ClienteId.of(command.getClienteId()),events);
        cliente.actualizarCliente(ClienteId.of(command.getClienteId()),
                new Nombre(command.getNombre()),
                new Apellido(command.getApellido()),
                        new Correo(command.getCorreo()),
                        new Telefono(command.getTelefono()));
        return cliente.getUncommittedChanges().stream().map(event ->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
