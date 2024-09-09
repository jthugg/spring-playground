package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.read.controller;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.ReadTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.BaseResponse;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.PathRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoReadController {

    private final ReadTodoPort readTodoPort;

    @GetMapping(PathRoutes.READ_ALL_TODOS)
    public HttpEntity<BaseResponse<List<Todo>>> readAllTodos() {
        List<Todo> todos = readTodoPort.getAll();
        return ResponseEntity.ok(BaseResponse.of(todos));
    }

    @GetMapping(PathRoutes.READ_SPECIFIC_TODO)
    public HttpEntity<BaseResponse<Todo>> readSpecificTodo(@PathVariable long todoId) {
        Todo todo = readTodoPort.get(todoId);
        return ResponseEntity.ok(BaseResponse.of(todo));
    }

}
