package com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoEdit;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatusUpdate;

public interface UpdateTodoPort {

    Todo updateStatus(TodoStatusUpdate statusUpdateCommand);

    Todo edit(TodoEdit editCommand);

}
