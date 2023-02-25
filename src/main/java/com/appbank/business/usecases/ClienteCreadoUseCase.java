package com.appbank.business.usecases;

import com.appbank.business.gateways.DomainEventRepository;
import com.appbank.business.gateways.EventBus;
import com.appbank.business.generic.DomainEvent;
import com.appbank.business.generic.UseCaseForCommand;
import com.appbank.domain.Cliente;
import com.appbank.domain.command.ClienteCreadoCommand;
import com.appbank.domain.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ClienteCreadoUseCase extends UseCaseForCommand <ClienteCreadoCommand> {

    private final DomainEventRepository repository;

    private final EventBus bus;

    public ClienteCreadoUseCase(DomainEventRepository repository, EventBus bus){
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<ClienteCreadoCommand> clienteCreadoCommandMono){
        return  clienteCreadoCommandMono.flatMapIterable(command -> {
            Cliente cliente = new Cliente(ClienteId.of(command.getClienteId()),new Nombre(command.getNombre()),
                    new Apellido(command.getApellido()),new Correo(command.getCorreo()),new Telefono(command.getTelefono()));
            return cliente.getUncommittedChanges();
        }).flatMap(event ->
                repository.saveEvent(event));
    }
}
