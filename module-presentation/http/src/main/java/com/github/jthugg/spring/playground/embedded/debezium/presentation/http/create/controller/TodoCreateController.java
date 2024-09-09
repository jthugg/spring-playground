package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.create.controller;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoCreate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.CreateTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.BaseResponse;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.PathRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoCreateController {

    private final CreateTodoPort createTodoPort;

    @PostMapping(PathRoutes.CREATE_TODO)
    public HttpEntity<BaseResponse<Todo>> createTodo(@RequestBody TodoCreate requestModel) {
        Todo todo = createTodoPort.create(requestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.of(todo));
    }

}
