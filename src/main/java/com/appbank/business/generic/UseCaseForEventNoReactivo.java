package com.appbank.business.generic;

import java.util.List;
import java.util.function.Function;

public abstract class UseCaseForEventNoReactivo <R extends DomainEvent> implements Function<R, List<DomainEvent>> {
    public abstract List<DomainEvent> apply(R event);
}