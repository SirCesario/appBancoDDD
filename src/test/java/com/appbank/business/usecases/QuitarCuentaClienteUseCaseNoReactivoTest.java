package com.appbank.business.usecases;

import com.appbank.business.gateways.RepositoryExample;
import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.Cliente;
import com.appbank.domain.command.QuitarCuentaClienteCommand;
import com.appbank.domain.events.CuentaEliminada;
import com.appbank.domain.values.ClienteId;
import com.appbank.domain.values.CuentaId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuitarCuentaClienteUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private QuitarCuentaClienteUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new QuitarCuentaClienteUseCaseNoReactivo(repository);
    }

    @Test
    void sucessFullScenario(){

        String id = String.valueOf(ClienteId.of("100"));
        String cuentaId = String.valueOf(CuentaId.of("400500"));


        QuitarCuentaClienteCommand command = new QuitarCuentaClienteCommand( id,cuentaId);

        // Configurar el repositorio para devolver una lista vacía de eventos
        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any())).thenReturn(new ArrayList<DomainEvent>());


        // Ejecutar el caso de uso
        List<DomainEvent> result = useCase.apply(command);

        // Verificar que el resultado contiene un evento CuentaClienteQuitada con el id del cliente
        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.get(0) instanceof CuentaEliminada);
        Assertions.assertEquals(id, result.get(0).aggregateRootId());
    }
}
