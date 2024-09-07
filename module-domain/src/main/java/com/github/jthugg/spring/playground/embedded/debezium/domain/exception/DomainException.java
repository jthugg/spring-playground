package com.github.jthugg.spring.playground.embedded.debezium.domain.exception;

public abstract class DomainException extends RootException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
