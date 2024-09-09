package com.github.jthugg.spring.playground.embedded.debezium.presentation.http;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatus;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.CreateTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.create.controller.TodoCreateController;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.PathRoutes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.PrintingResultHandler;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TodoCreateController.class)
@ContextConfiguration(classes = TestContextConfiguration.class)
public class CreateControllerTest {

    @MockBean
    CreateTodoPort createTodoPort;

    @Autowired
    MockMvc mvc;

    @Test
    void createTest() throws Exception {
        Todo expectedTodo = new Todo(1, "testTitle", "testContent", TodoStatus.READY, Instant.now());

        String uri = PathRoutes.CREATE_TODO;
        String requestBody = "{\"title\": \"testTitle\", \"content\": \"testContent\"}";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

}
