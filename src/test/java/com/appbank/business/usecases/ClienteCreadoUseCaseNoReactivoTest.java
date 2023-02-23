package com.appbank.business.usecases;

import com.appbank.business.gateways.DomainEventRepository;
import com.appbank.business.gateways.RepositoryExample;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.command.ClienteCreadoCommand;
import com.appbank.domain.entitys.Cuenta;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteCreadoUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;
    private ClienteCreadoUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){useCase = new ClienteCreadoUseCaseNoReactivo(repository);}

    @Test
    void successFullScenario(){

        String clienteId = "80500500";
        String nombre = "Pepito";
        String apellido = "Perez";
        String correo = "pepe@gmail.com";
        String telefono = "300500500";


        ClienteCreadoCommand command = new ClienteCreadoCommand(clienteId,nombre,apellido,correo,telefono);

        ClienteCreado event = new ClienteCreado(ClienteId.of(clienteId),new Nombre(nombre),new Apellido(apellido),new Correo(correo),
                new Telefono(telefono));
        event.setAggregateRootId(clienteId);

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(ClienteCreado.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        List<DomainEvent> result = useCase.apply(command);

        Assertions.assertEquals(event.aggregateRootId(),result.get(0).aggregateRootId());



    }

}