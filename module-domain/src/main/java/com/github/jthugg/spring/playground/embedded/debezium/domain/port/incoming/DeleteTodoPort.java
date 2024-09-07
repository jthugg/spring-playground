package com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming;

public interface DeleteTodoPort {

    void delete(long id);

}
