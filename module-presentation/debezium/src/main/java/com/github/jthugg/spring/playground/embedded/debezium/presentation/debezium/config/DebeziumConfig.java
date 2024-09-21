package com.github.jthugg.spring.playground.embedded.debezium.presentation.debezium.config;

import io.debezium.connector.mysql.MySqlConnector;
import io.debezium.storage.file.history.FileSchemaHistory;
import org.apache.kafka.connect.storage.FileOffsetBackingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class DebeziumConfig {

    @Bean
    public Executor debeziumExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public io.debezium.config.Configuration debeziumEngine(
            @Value("${debezium.db-host}") String dbHost,
            @Value("${debezium.db-port}") String dbPort,
            @Value("${debezium.db-user}") String dbUser,
            @Value("${debezium.db-password}") String dbPassword,
            @Value("${debezium.table-include-list}") String tableIncludeList
    ) {
        Properties properties = new Properties();
        properties.setProperty("name", "embedded-debezium-demo");
        properties.setProperty("connector.class", MySqlConnector.class.getCanonicalName());
        properties.setProperty("offset.storage", FileOffsetBackingStore.class.getCanonicalName());
        File offsetStorageFile = new File("offsets.dat");
        properties.setProperty("offset.storage.file.filename", offsetStorageFile.getAbsolutePath());
        properties.setProperty("offset.flush.interval.ms", "60000"); // 60_000 ms
        properties.setProperty("database.hostname", dbHost);
        properties.setProperty("database.port", dbPort);
        properties.setProperty("database.user", dbUser);
        properties.setProperty("database.password", dbPassword);
        properties.setProperty("database.server.id", "1234");
        properties.setProperty("database.allowPublicKeyRetrieval", "true");
        properties.setProperty("topic.prefix", "debezium");
        properties.setProperty("table.include.list", tableIncludeList);
        properties.setProperty("tombstones.on.delete", "false");
        properties.setProperty("plugin.name", "test-plugin");
        properties.setProperty("provide.transaction.metadata", "true");
        properties.setProperty("schema.history.internal", FileSchemaHistory.class.getCanonicalName());
        File schemaHistoryFile = new File("schemaHistory.dat");
        properties.setProperty("schema.history.internal.file.filename", schemaHistoryFile.getAbsolutePath());
        return io.debezium.config.Configuration.from(properties);
    }

}
