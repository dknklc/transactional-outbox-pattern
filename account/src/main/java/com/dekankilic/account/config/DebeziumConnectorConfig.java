package com.dekankilic.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConnectorConfig {

    @Bean
    public io.debezium.config.Configuration configuration(){
        return io.debezium.config.Configuration.create()
                .with("name", "outbox-mysql") // Debezium konektorunun adini belirtir.
                .with("database.hostname", "localhost") // MYSQL veritabaninin ana bilgisayar adini belirtir.
                .with("database.port", "3306") // MYSQL veritabaninin baglanti noktasini belirtir.
                .with("database.user", "root") // MYSQL veritabani kullanici adini belirtir.
                .with("database.password", "secret") // MYSQL veritabani kullanici parolasini belirtir.
                .with("database.include.list", "outbox-pattern")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector") // MYSQL veritabani icin kullanilacak Debezium konektorunu belirtir.
                .with("skipped.operations", "t,d") // Atlanan islem turlerini belirtir. // c for inserts/create, u for updates, d for deletes, t for truncates, and none to not skip any operations
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore") // Offset bilgilerinin saklanacagi depo turunu belirtir.
                .with("offset.flush.interval.ms", 60000) // Offset bilgilerinin ne siklikta kaydedilecegini belirtir (milisaniye cinsinden).
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", "schistory.dat")
                .with("topic.prefix", "mysqlcapturedchanges") // Kafka topiclerinin on ekini belirtir.
                .with("decimal.handling.mode", "string") // Ondalik sayilari isleme modunu belirtir.
                .with("table.include.list", "outbox") // Izlenmesini istediginiz PostgreSQL tablosunu belirtir.
                .with("tasks.max", "1") // Eszamanli gorev sayisini belirtir.
        .build();
    }


}
