package com.appbank.business.generic;

import java.util.List;
import java.util.function.Function;

public abstract class UseCaseForCommandNoReactivo <R extends Command> implements Function<R, List<DomainEvent>> {
    public abstract List<DomainEvent> apply(R command);
}
