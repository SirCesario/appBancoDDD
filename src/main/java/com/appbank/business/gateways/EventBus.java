package com.appbank.business.gateways;


import com.appbank.business.generic.DomainEvent;

public interface EventBus {
    void publish(DomainEvent event);

    void publishError(Throwable errorEvent);
}
