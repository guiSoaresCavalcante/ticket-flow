package br.com.eventsrv.infrastructure.batch;

import br.com.eventsrv.infrastructure.adapter.out.database.event.entity.EventEntity;
import br.com.eventsrv.infrastructure.adapter.out.database.event.repository.EventJpaRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class EventImportBatchConfig {

    private static final int CHUNK_SIZE = 100;

    @Bean
    public FlatFileItemReader<EventEntity> eventCsvReader() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        DefaultLineMapper<EventEntity> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(eventFieldSetMapper());

        FlatFileItemReader<EventEntity> reader = new FlatFileItemReader<>(new ClassPathResource("import.csv"), lineMapper);
        reader.setLinesToSkip(1);
        reader.setSkippedLinesCallback(header -> tokenizer.setNames(
                Arrays.stream(header.split(",")).map(String::trim).toArray(String[]::new)));
        return reader;
    }

    @Bean
    public ItemWriter<EventEntity> eventWriter(EventJpaRepository repository) {
        return chunk -> repository.saveAll(chunk.getItems());
    }

    @Bean
    public Step importEventsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                                 FlatFileItemReader<EventEntity> eventCsvReader, ItemWriter<EventEntity> eventWriter) {
        return new StepBuilder("importEventsStep", jobRepository)
                .<EventEntity, EventEntity>chunk(CHUNK_SIZE)
                .reader(eventCsvReader)
                .writer(eventWriter)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job importEventsJob(JobRepository jobRepository, Step importEventsStep) {
        return new JobBuilder("importEventsJob", jobRepository)
                .start(importEventsStep)
                .build();
    }

    private FieldSetMapper<EventEntity> eventFieldSetMapper() {
        return fieldSet -> {
            LocalDateTime now = LocalDateTime.now();
            return new EventEntity(
                    null,
                    fieldSet.readString("name"),
                    blankToNull(fieldSet.readString("description")),
                    fieldSet.readString("eventType"),
                    LocalDateTime.parse(fieldSet.readString("startAt")),
                    blankToNull(fieldSet.readString("endAt")) == null ? null : LocalDateTime.parse(fieldSet.readString("endAt")),
                    fieldSet.readString("status"),
                    blankToNull(fieldSet.readString("venueId")) == null ? null : UUID.fromString(fieldSet.readString("venueId")),
                    UUID.fromString(fieldSet.readString("organizerId")),
                    now,
                    now
            );
        };
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value;
    }
}
