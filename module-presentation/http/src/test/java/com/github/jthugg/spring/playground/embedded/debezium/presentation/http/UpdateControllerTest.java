package com.github.jthugg.spring.playground.embedded.debezium.presentation.http;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoEdit;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatus;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatusUpdate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.incoming.UpdateTodoPort;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.update.controller.TodoUpdateController;
import com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util.PathRoutes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
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

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TodoUpdateController.class)
@ContextConfiguration(classes = TestContextConfiguration.class)
public class UpdateControllerTest {

    @MockBean
    UpdateTodoPort updateTodoPort;

    @Autowired
    MockMvc mvc;

    @Test
    void statusUpdateTest() throws Exception {
        String uri = PathRoutes.UPDATE_TODO_STATUS.replace("{todoId}", "1");
        String requestBody = "{\"status\":\"done\"}";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody);

        Mockito.when(updateTodoPort.updateStatus(ArgumentMatchers.any(TodoStatusUpdate.class)))
                .thenReturn(new Todo(1, "testTitle", "testContent", TodoStatus.DONE, Instant.now()));

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void editTest() throws Exception {
        String uri = PathRoutes.EDIT_TODO.replace("{todoId}", "1");
        String requestBody = "{\"title\":\"new title\", \"content\":\"new content\"}";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody);

        Mockito.when(updateTodoPort.edit(ArgumentMatchers.any(TodoEdit.class)))
                .thenReturn(new Todo(1, "new title", "new content", TodoStatus.READY, Instant.now()));

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
