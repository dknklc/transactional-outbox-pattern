package com.dekankilic.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumConnectorConfig {

    @Bean
    public io.debezium.config.Configuration configuration(){
        return io.debezium.config.Configuration.create()
                .with("name", "outbox-mysql") // Debezium konektorunun adini belirtir.
                .with("database.server.name", "outbox_pattern") // Kafka tarafinda kullanilacak olan veritabani sunucusunun adini belirtir.
                .with("database.hostname", "localhost") // MYSQL veritabaninin ana bilgisayar adini belirtir.
                .with("database.port", "3306") // MYSQL veritabaninin baglanti noktasini belirtir.
                .with("database.user", "root") // MYSQL veritabani kullanici adini belirtir.
                .with("database.password", "secret") // MYSQL veritabani kullanici parolasini belirtir.
                .with("database.dbname", "outbox-pattern") // MYSQL veritabani adini belirtir.
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector") // MYSQL veritabani icin kullanilacak Debezium konektorunu belirtir.
                .with("skipped.operations", "t,d") // Atlanan islem turlerini belirtir. // c for inserts/create, u for updates, d for deletes, t for truncates, and none to not skip any operations
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore") // Offset bilgilerinin saklanacagi depo turunu belirtir.
                .with("offset.storage.file.filename", "offset.dat") // Offset bilgilerinin saklanacagi dosyanin yolunu belirtir.
                .with("offset.flush.interval.ms", 60000) // Offset bilgilerinin ne siklikta kaydedilecegini belirtir (milisaniye cinsinden).
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", "schistory.dat")
                .with("topic.prefix", "mysqlcapturedchanges") // Kafka topiclerinin on ekini belirtir.
                .with("decimal.handling.mode", "string") // Ondalik sayilari isleme modunu belirtir.
                .with("table.include.list", "public.outbox") // Izlenmesini istediginiz PostgreSQL tablosunu belirtir.
                .with("tasks.max", "1") // Eszamanli gorev sayisini belirtir.
                .with("tombstones.on.delete", "false") // Silme islemlerini belirli bir sekilde islemenin ayarini belirtir.
                .with("route.topic.regex", "") // Konu yonlendirmesi icin regex deseni belirtir.
        .build();
    }


}
