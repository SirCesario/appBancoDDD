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

public class AgregarCuentaClienteUseCase extends UseCaseForCommand <AgregarCuentaClienteCommand> {

    private final DomainEventRepository repository;

    private EventBus bus;


    AgregarCuentaClienteUseCase(DomainEventRepository repository, EventBus bus){
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<AgregarCuentaClienteCommand> agregarCuentaClienteCommandMono){

        return agregarCuentaClienteCommandMono.flatMapIterable(command -> {
            Cliente cliente = new Cliente(ClienteId.of("7"),
                    new Nombre("Laura"),
                    new Apellido("Rodriguez"),
                    new Correo("soporte12@gmail.com"),
                    new Telefono("8010203"));
            return cliente.getUncommittedChanges();

        }).flatMap(repository::saveEvent);

    }
}
