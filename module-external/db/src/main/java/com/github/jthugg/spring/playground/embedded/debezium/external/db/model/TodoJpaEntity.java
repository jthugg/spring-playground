package com.github.jthugg.spring.playground.embedded.debezium.external.db.model;

import com.github.jthugg.spring.playground.embedded.debezium.domain.model.Todo;
import com.github.jthugg.spring.playground.embedded.debezium.domain.model.TodoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

@Entity
@Getter
@Table(name = TodoJpaEntity.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoJpaEntity implements Persistable<Long> {

    public static final String TABLE_NAME = "TODO";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "TITLE")
    private String title;

    @Setter
    @Column(name = "CONTENT")
    private String content;

    @Setter
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP(6)")
    private Instant createdAt;

    @Column(name = "REMOVED_AT", columnDefinition = "TIMESTAMP(6)")
    private Instant removedAt;

    public TodoJpaEntity(Todo domainEntity) {
        this.id = domainEntity.getId();
        this.title = domainEntity.getTitle();
        this.content = domainEntity.getContent();
        this.todoStatus = domainEntity.getStatus();
        this.createdAt = domainEntity.getCreatedAt();
    }

    public TodoJpaEntity(String title, String content) {
        this.title = title;
        this.content = content;
        this.todoStatus = TodoStatus.READY;
    }

    public Todo toDomainEntity() {
        return new Todo(this.id, this.title, this.content, this.todoStatus, this.createdAt);
    }

    public void remove() {
        this.removedAt = Instant.now();
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

}
