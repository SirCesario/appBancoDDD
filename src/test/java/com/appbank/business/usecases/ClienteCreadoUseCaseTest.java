package com.appbank.business.usecases;

import com.appbank.business.gateways.DomainEventRepository;
import com.appbank.business.gateways.EventBus;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.command.ClienteCreadoCommand;
import com.appbank.domain.events.ClienteCreado;
import com.appbank.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ClienteCreadoUseCaseTest {


    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    public ClienteCreadoUseCase useCase;

    @BeforeEach
    void setUp(){ useCase = new ClienteCreadoUseCase(repository, bus); }

    @Test
    void successFullScenario(){

            String ID = "500";
            String nombre = "Cesar";
            String apellido = "Cardenas";
            String correo = "tecsfro@gmail.com";
            String telefono = "8181880";

            ClienteCreadoCommand command = new ClienteCreadoCommand(ID,nombre,apellido,correo,telefono);

            ClienteCreado clienteCreado = new ClienteCreado(new ClienteId("800"),new Nombre("Julio"),new Apellido("Cuevas"),
                    new Correo("pepito15@gmail.com"),new Telefono("300500500"));
            clienteCreado.setAggregateRootId(ID);

            Mockito.when(repository.saveEvent(ArgumentMatchers.any())).thenAnswer(invocationOnMock -> {
                return Mono.just(invocationOnMock.getArgument(0));
            });

//        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

                Flux<DomainEvent> result = useCase.apply(Mono.just(command));

                StepVerifier.create(result).expectNextMatches(event1 ->{
                    Assertions.assertEquals(event1.aggregateRootId(),clienteCreado.aggregateRootId());
                    return event1.aggregateRootId().equals(clienteCreado.aggregateRootId());
                })
                        .verifyComplete();


    }

}