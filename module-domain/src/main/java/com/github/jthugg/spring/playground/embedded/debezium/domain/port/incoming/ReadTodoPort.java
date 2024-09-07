package com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;

import java.util.List;

public interface ReadTodoPort {

    List<Todo> getAll();

    Todo get(long id);

}
