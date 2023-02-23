package com.appbank.business.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public abstract class  UseCaseForEvent <R extends DomainEvent> implements Function<Mono<R>, Flux<DomainEvent>> {
    public abstract Flux<DomainEvent> apply(Mono<R> rMono);
}
