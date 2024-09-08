package com.github.jthugg.spring.playground.embedded.debezium.external.db.repository;

import com.github.jthugg.spring.playground.embedded.debezium.external.db.model.TodoJpaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TodoJpaRepository extends CrudRepository<TodoJpaEntity, Long> {

    @NonNull
    @Override
    @Query("SELECT todo FROM TodoJpaEntity todo WHERE todo.removedAt IS NULL")
    List<TodoJpaEntity> findAll();

}
