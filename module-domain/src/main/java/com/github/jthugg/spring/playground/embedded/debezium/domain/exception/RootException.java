package com.github.jthugg.spring.playground.embedded.debezium.domain.exception;

public abstract class RootException extends RuntimeException {

    public RootException(String message) {
        super(message);
    }

    public RootException(String message, Throwable cause) {
        super(message, cause);
    }

}
