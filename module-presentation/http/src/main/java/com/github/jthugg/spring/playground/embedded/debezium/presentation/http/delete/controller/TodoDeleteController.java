package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.delete.controller;

import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.DeleteTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.BaseResponse;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.PathRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoDeleteController {

    private final DeleteTodoPort deleteTodoPort;

    @DeleteMapping(PathRoutes.DELETE_TODO)
    public HttpEntity<BaseResponse<Void>> deleteTodo(@PathVariable long todoId) {
        deleteTodoPort.delete(todoId);
        return ResponseEntity.ok(BaseResponse.voidOf());
    }

}
