package com.appbank.business.usecases;

import com.appbank.business.gateways.DomainEventRepository;
import com.appbank.business.gateways.EventBus;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.command.AgregarCuentaClienteCommand;
import com.appbank.domain.events.ClienteCreado;
import com.appbank.domain.events.CuentaAgregada;
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
class AgregarCuentaClienteUseCaseTest {


    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private AgregarCuentaClienteUseCase useCase;

    @BeforeEach
    void setUp(){ useCase = new AgregarCuentaClienteUseCase(repository,bus); }

    @Test
    void successFullScenario(){

         String clienteId = "15";
         String cuentaId = "87";
         String fechaCreacionCuenta = "2016-10-05";
         String tipoCuenta = "Ahorros";
         String saldo = "6000000";

        AgregarCuentaClienteCommand command = new AgregarCuentaClienteCommand
                (clienteId,cuentaId,fechaCreacionCuenta,tipoCuenta,saldo);

        ClienteCreado event = new ClienteCreado(new ClienteId("7"),
                new Nombre("Vane"),new Apellido("Mora"),
                new Correo("vanemora@gmail.com"),
                new Telefono("3002809090"));
        event.setAggregateRootId(clienteId);

//        Mockito.doAnswer(i ->null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));
//        Mockito.when(repository.saveEvent(ArgumentMatchers.any(ClienteCreado.class)))
//               .thenAnswer(invocationOnMock -> (invocationOnMock.getArgument(1)));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

//        StepVerifier.create(result).expectNextMatches(event1 ->{
//            CuentaAgregada cuentaAgregada = (CuentaAgregada) event1;
//            Assertions.assertEquals(cuentaAgregada.getCuentaId().value(), event.getClienteId().value());
//            return  event1.aggregateRootId().equals(event.aggregateRootId());
//        }).verifyComplete();
    }
}