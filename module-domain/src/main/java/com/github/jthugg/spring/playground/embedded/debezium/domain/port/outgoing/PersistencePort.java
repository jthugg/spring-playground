package com.github.jthugg.spring.playground.embedded.debezium.domain.port.outgoing;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoCreate;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PersistencePort {

    @NonNull
    Todo create(TodoCreate todoCreate);

    /**
     * find a specific instance.
     *
     * @param id identifier of instance that you want to find
     * @return specific instance
     * @throws com.github.jthugg.spring.playground.embedded.debezium.domain.exception.OutgoingPortException when there's no element of argued identifier
     */
    @NonNull
    Todo find(long id);

    /**
     * find all instances.
     *
     * @return list of instances - never null
     */
    @NonNull
    List<Todo> findAll();

    @NonNull
    Todo update(Todo todo);

    void delete(long id);

}
