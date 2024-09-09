package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.update.controller;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoEdit;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatusUpdate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.UpdateTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.BaseResponse;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.PathRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoUpdateController {

    private final UpdateTodoPort updateTodoPort;

    @PatchMapping(PathRoutes.UPDATE_TODO_STATUS)
    public HttpEntity<BaseResponse<Todo>> updateTodoStatus(TodoStatusUpdate updateCommand) {
        Todo todo = updateTodoPort.updateStatus(updateCommand);
        return ResponseEntity.ok(BaseResponse.of(todo));
    }

    @PutMapping(PathRoutes.EDIT_TODO)
    public HttpEntity<BaseResponse<Todo>> editTodo(TodoEdit editCommand) {
        Todo todo = updateTodoPort.edit(editCommand);
        return ResponseEntity.ok(BaseResponse.of(todo));
    }

}
