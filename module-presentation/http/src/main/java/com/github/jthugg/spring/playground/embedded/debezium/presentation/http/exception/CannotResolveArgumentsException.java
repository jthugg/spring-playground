package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.exception;

import com.github.jthugg.spring.playground.embedded.debezium.domain.exception.IncomingPortException;

public class CannotResolveArgumentsException extends IncomingPortException {

    public static final String MESSAGE = "Cannot resolve arguments from request";

    public CannotResolveArgumentsException() {
        super(MESSAGE);
    }

    public CannotResolveArgumentsException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public CannotResolveArgumentsException(String message) {
        super(message);
    }

    public CannotResolveArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

}
