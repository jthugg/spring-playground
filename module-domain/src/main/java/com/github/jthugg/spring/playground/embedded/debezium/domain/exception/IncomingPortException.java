package com.github.jthugg.spring.playground.embedded.debezium.domain.exception;

public class IncomingPortException extends RootException {

    public IncomingPortException(String message) {
        super(message);
    }

    public IncomingPortException(String message, Throwable cause) {
        super(message, cause);
    }

}
