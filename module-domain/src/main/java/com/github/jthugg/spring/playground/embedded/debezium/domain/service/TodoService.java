package com.github.jthugg.spring.playground.embedded.debezium.domain.service;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoCreate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoEdit;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatusUpdate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.CreateTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.DeleteTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.ReadTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.UpdateTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.outgoing.PersistencePort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService implements CreateTodoPort, ReadTodoPort, UpdateTodoPort, DeleteTodoPort {

    private final PersistencePort persistencePort;

    @Override
    @Transactional
    public Todo create(@NonNull TodoCreate todoCreate) {
        return persistencePort.create(todoCreate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Todo> getAll() {
        return persistencePort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Todo get(long id) {
        return persistencePort.find(id);
    }

    @Override
    @Transactional
    public Todo updateStatus(TodoStatusUpdate statusUpdateCommand) {
        Todo todo = persistencePort.find(statusUpdateCommand.id());
        if (todo.getStatus() == statusUpdateCommand.status()) {
            return todo;
        }
        todo.setStatus(statusUpdateCommand.status());
        todo = persistencePort.update(todo);
        return todo;
    }

    @Override
    @Transactional
    public Todo edit(TodoEdit editCommand) {
        Todo todo = persistencePort.find(editCommand.id());
        if (todo.getTitle().equals(editCommand.title()) && todo.getContent().equals(editCommand.content())) {
            return todo;
        }
        todo.setTitle(editCommand.title());
        todo.setContent(editCommand.content());
        todo = persistencePort.update(todo);
        return todo;
    }

    @Override
    @Transactional
    public void delete(long id) {
        persistencePort.delete(id);
    }

}
