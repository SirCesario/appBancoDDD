package com.appbank.business.usecases;

import com.appbank.business.gateways.RepositoryExample;
import com.appbank.business.generic.DomainEvent;
import com.appbank.business.generic.UseCaseForCommandNoReactivo;

import com.appbank.domain.Cliente;
import com.appbank.domain.command.AgregarCuentaClienteCommand;
import com.appbank.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;


public class AgregarCuentaClienteUseCaseNoReactivo extends UseCaseForCommandNoReactivo<AgregarCuentaClienteCommand> {

        private RepositoryExample repository;

        public AgregarCuentaClienteUseCaseNoReactivo(RepositoryExample repository) {
            this.repository = repository;
        }

        @Override
        public List<DomainEvent> apply(AgregarCuentaClienteCommand command) {
            List<DomainEvent>events = repository.findByIdNoReactivo(command.getClienteId().toString());
            Cliente cliente = Cliente.from(ClienteId.of(command.getClienteId().toString()),events);
            cliente.agregarCuenta(CuentaId.of(command.getCuentaId()),new ClienteId(command.getClienteId()),
                    new FechaCreacionCuenta(command.getFechaCreacionCuenta()),
                    new TipoCuenta(command.getTipoCuenta()),
                    new Saldo(command.getSaldo()));
            return cliente.getUncommittedChanges().stream().map(event ->{
                return repository.saveEventNoReactivo(event);
            }).collect(Collectors.toList());


    }

}
