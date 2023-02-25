package com.appbank.business.usecases;

import com.appbank.business.gateways.DomainEventRepository;
import com.appbank.business.gateways.EventBus;
import com.appbank.business.generic.DomainEvent;
import com.appbank.business.generic.UseCaseForCommand;
import com.appbank.domain.Cliente;
import com.appbank.domain.command.AgregarCuentaClienteCommand;
import com.appbank.domain.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgregarCuentaClienteEventUseCase extends UseCaseForCommand<AgregarCuentaClienteCommand> {

    private final DomainEventRepository repository;

    private  final EventBus bus;

    AgregarCuentaClienteEventUseCase(DomainEventRepository repository, EventBus bus){
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AgregarCuentaClienteCommand> agregarCuentaClienteCommandMono){
        return agregarCuentaClienteCommandMono.flatMapMany(command -> repository.findById(command.getClienteId())).
                collectList().flatMapIterable(events -> {
                    Cliente cliente = new Cliente(ClienteId.of("16"),new Nombre("Sofia"),new Apellido("Cuevas"),new Correo("Elizabeth15@gmail.com"),
                            new Telefono("7824040"));
                    return cliente.getUncommittedChanges();
                }).map(event ->{
                    bus.publish(event);
                    return event;
                }).flatMap(event -> repository.saveEvent(event));
    }
}
