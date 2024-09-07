package com.github.jthugg.spring.playground.embedded.debezium.domain.exception;

public class OutgoingPortException extends RootException {

    public OutgoingPortException(String message) {
        super(message);
    }

    public OutgoingPortException(String message, Throwable cause) {
        super(message, cause);
    }

}
