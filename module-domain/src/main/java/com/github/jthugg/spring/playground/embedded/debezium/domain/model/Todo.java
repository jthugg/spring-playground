package com.github.jthugg.spring.playground.embedded.debezium.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Todo {

    private final long id;
    private String title;
    private String content;
    private TodoStatus status;
    private final Instant createdAt;

    public Todo(long id, String title, String content, TodoStatus status, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
    }

}
