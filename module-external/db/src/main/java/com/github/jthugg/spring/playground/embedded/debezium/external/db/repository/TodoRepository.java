package com.github.jthugg.spring.playground.embedded.debezium.external.db.repository;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoCreate;
import com.github.jthugg.spring.playground.embedded.debezium.domain.port.outgoing.PersistencePort;
import com.github.jthugg.spring.playground.embedded.debezium.external.db.exception.NoEntityFoundException;
import com.github.jthugg.spring.playground.embedded.debezium.external.db.model.TodoJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository implements PersistencePort {

    private final TodoJpaRepository jpaRepository;

    @NonNull
    @Override
    public Todo create(TodoCreate todoCreate) {
        TodoJpaEntity jpaEntity = new TodoJpaEntity(todoCreate.title(), todoCreate.content());
        jpaEntity = jpaRepository.save(jpaEntity);
        return jpaEntity.toDomainEntity();
    }

    @NonNull
    @Override
    public Todo find(long id) throws NoEntityFoundException {
        TodoJpaEntity jpaEntity = jpaRepository.findById(id)
                .orElseThrow(NoEntityFoundException::new);
        return jpaEntity.toDomainEntity();
    }

    @NonNull
    @Override
    public List<Todo> findAll() {
        List<TodoJpaEntity> jpaEntities = jpaRepository.findAll();
        return jpaEntities.stream()
                .map(TodoJpaEntity::toDomainEntity)
                .toList();
    }

    @NonNull
    @Override
    public Todo update(Todo todo) {
        jpaRepository.save(new TodoJpaEntity(todo));
        return todo;
    }

    @Override
    public void delete(long id) {
        jpaRepository.findById(id).ifPresent(TodoJpaEntity::remove);
    }

}
