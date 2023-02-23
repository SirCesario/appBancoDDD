package com.appbank.business.usecases;

import com.appbank.business.gateways.RepositoryExample;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgregarCuentaClienteUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private AgregarCuentaClienteUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new AgregarCuentaClienteUseCaseNoReactivo(repository);
    }

    @Test
    void successFullScenario(){

        String clienteId = "80500";
        String cuentaId = "400500600";
        String fechaCreacionCuenta = "2023-01-02";
        String tipoCuenta = "Ahorros";
        String saldo = "5000000";

        AgregarCuentaClienteCommand command = new AgregarCuentaClienteCommand(clienteId,cuentaId,fechaCreacionCuenta,tipoCuenta,saldo);

        ClienteCreado clienteCreado = new ClienteCreado(new ClienteId(clienteId),new Nombre("Cesar"),new Apellido("Cardenas"),
                new Correo("cesario@gmail.com"),new Telefono("8181870"));
        clienteCreado.setAggregateRootId(clienteId);

        Mockito.when(repository.findByIdNoReactivo(clienteId)).thenReturn(List.of(clienteCreado));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(CuentaAgregada.class))).thenAnswer(interceptor ->{
            return interceptor.getArgument(0);
        });

        List<DomainEvent> result = useCase.apply(command);


        Assertions.assertEquals(command.getClienteId(),result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(CuentaAgregada.class, result.get(0));





    }
}