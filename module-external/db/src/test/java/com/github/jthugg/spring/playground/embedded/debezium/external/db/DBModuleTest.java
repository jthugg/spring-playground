package com.github.jthugg.spring.playground.embedded.debezium.external.db;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoCreate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatus;
import com.github.jthugg.spring.playground.embedded.debezium.external.db.exception.NoEntityFoundException;
import com.github.jthugg.spring.playground.embedded.debezium.external.db.repository.TodoJpaRepository;
import com.github.jthugg.spring.playground.embedded.debezium.external.db.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@Import(TodoRepository.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:/sql/sql.sql")
@ContextConfiguration(classes = TestConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.config.location=classpath:/application-test.yml")
public class DBModuleTest {

    @Autowired
    TodoJpaRepository todoJpaRepository;

    @Autowired
    TodoRepository todoRepository;

    @Test
    void createTest() {
        String testTitle = "test title";
        String testContent = "test content";
        Todo todo = todoRepository.create(new TodoCreate(testTitle, testContent));
        Assertions.assertNotNull(todo);
        Assertions.assertEquals(testTitle, todo.getTitle());
        Assertions.assertEquals(testContent, todo.getContent());
        System.out.println(todo.getId());
    }

    @Test
    void readTest() {
        Assertions.assertThrows(NoEntityFoundException.class, () -> todoRepository.find(0));
    }

    @Nested
    public class ReadTests {

        @Autowired
        TodoRepository todoRepository;

        @BeforeEach
        void setup() {
            todoRepository.create(new TodoCreate("test title", "test content"));
        }

        @Test
        void test() {
            List<Todo> todos = todoRepository.findAll();

            Assertions.assertFalse(todos.isEmpty());

            System.out.println(todos.getFirst().getId());
        }

    }

    @Nested
    public class UpdateTests {

        @Autowired
        TodoRepository todoRepository;

        @BeforeEach
        void setup() {
            Todo todo = todoRepository.create(new TodoCreate("test title", "test content"));

            todo.setTitle("modified title");
            todo.setContent("modified content");
            todo.setStatus(TodoStatus.DONE);
            todoRepository.update(todo);
        }

        @Test
        void test() {
            Todo todo = todoRepository.find(1);
            Assertions.assertEquals(TodoStatus.DONE, todo.getStatus());
            Assertions.assertEquals("modified title", todo.getTitle());
            Assertions.assertEquals("modified content", todo.getContent());
        }

    }

    @Nested
    public class DeleteTest {

        @Autowired
        TodoRepository todoRepository;

        @BeforeEach
        void setup() {
            Todo todo = todoRepository.create(new TodoCreate("test title", "test content"));

            todoRepository.delete(todo.getId());
        }

        @Test
        void test() {
            List<Todo> todos = todoRepository.findAll();

            Assertions.assertTrue(todos.isEmpty());
        }

    }

}
