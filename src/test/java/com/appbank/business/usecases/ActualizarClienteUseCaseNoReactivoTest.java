package com.appbank.business.usecases;

import com.appbank.business.gateways.RepositoryExample;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.command.ActualizarClienteCommand;
import com.appbank.domain.events.ClienteActualizado;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ActualizarClienteUseCaseNoReactivoTest {


    @Mock
    private RepositoryExample repository;

    private ActualizarClienteUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new ActualizarClienteUseCaseNoReactivo(repository);
    }

    @Test
    void sucessFullScenario(){

        ClienteId ID = ClienteId.of("100");
        String NOMBRE = "Andres";
        String APELLIDO = "Suarez";
        String CORREO = "andresua@gmail.com";
        String TELEFONO = "2777777";

        ActualizarClienteCommand command = new ActualizarClienteCommand(ID,NOMBRE,APELLIDO,CORREO,TELEFONO);

        ClienteCreado event = new ClienteCreado(new ClienteId("144"),new Nombre("Julio"),new Apellido("Cuevas"),new Correo("jc123@gmail.com"),new Telefono("3125656543"));
        event.setAggregateRootId(String.valueOf(ID));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(ClienteActualizado.class))).thenAnswer(invocationOnMock ->
        {
            return invocationOnMock.getArgument(0);
        });

        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(ID, result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(ClienteActualizado.class, result.get(0));
    }

}