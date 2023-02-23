package com.appbank.business.gateways;


import com.appbank.business.generic.DomainEvent;
import com.appbank.domain.Cliente;

import reactor.core.publisher.Flux;

import java.util.List;


public interface RepositoryExample {

    DomainEvent saveEventNoReactivo(DomainEvent event);
    List<DomainEvent> findByIdNoReactivo(String agregateRootId);

    void deleteById(String id);

    Flux<Cliente> findAll();

}
