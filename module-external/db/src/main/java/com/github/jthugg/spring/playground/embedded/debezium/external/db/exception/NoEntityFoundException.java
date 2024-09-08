package com.github.jthugg.spring.playground.embedded.debezium.external.db.exception;

import com.github.jthugg.spring.playground.embedded.debezium.domain.exception.OutgoingPortException;

public class NoEntityFoundException extends OutgoingPortException {

    public static final String MESSAGE = "There's no Entity found.";

    public NoEntityFoundException() {
        super(MESSAGE);
    }

    public NoEntityFoundException(String message) {
        super(message);
    }

    public NoEntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
