package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util;

public class PathRoutes {

    public static final String CREATE_TODO = "/api/todos"; // POST

    public static final String READ_ALL_TODOS = "/api/todos"; // GET

    public static final String READ_SPECIFIC_TODO = "/api/todos/{todoId}"; // GET

    public static final String UPDATE_TODO_STATUS = "/api/todos/{todoId}"; // PATCH

    public static final String EDIT_TODO = "/api/todos/{todoId}"; // PUT

    public static final String DELETE_TODO = "/api/todos/{todoId}"; // DELETE

}
