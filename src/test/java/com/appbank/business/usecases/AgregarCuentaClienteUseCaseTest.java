package com.appbank.business.usecases;

import com.appbank.business.gateways.DomainEventRepository;
import com.appbank.business.gateways.EventBus;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.command.AgregarCuentaClienteCommand;
import com.appbank.domain.events.ClienteCreado;
import com.appbank.domain.values.*;
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
    EventBus bus;

    protected AgregarCuentaClienteUseCase useCase;

    @BeforeEach
    void setUp(){ useCase = new AgregarCuentaClienteUseCase(repository,bus); }

    @Test
    void successFullScenario(){
        String clienteId = "clienteId-Test";
        String cuentaId = "cuentaId-Test";
        String fechaCreacionCuenta = "fechaCreacionCuenta-Test";
        String tipoCuenta = "tipoCuenta-Test";
        String saldo = "saldoCuenta-Test";

        AgregarCuentaClienteCommand command = new AgregarCuentaClienteCommand(clienteId,cuentaId,fechaCreacionCuenta,tipoCuenta,saldo);

        ClienteCreado clienteCreado = new ClienteCreado(new ClienteId(clienteId),new Nombre("Probando"),
                new Apellido("prueba"),
                new Correo("pruebaAdmin@gmail.com"),
                new Telefono("3007809090"));
        clienteCreado.setAggregateRootId(clienteId);

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));




    }

}