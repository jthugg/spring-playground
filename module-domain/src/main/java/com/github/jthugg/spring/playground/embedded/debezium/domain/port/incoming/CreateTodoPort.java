package com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoCreate;

public interface CreateTodoPort {

    Todo create(TodoCreate todoCreate);

}
