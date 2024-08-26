package org.example.config;

import lombok.RequiredArgsConstructor;
import org.quartz.spi.JobFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
@Import(TicketingAlertJobConfig.class)
@RequiredArgsConstructor
@EnableBatchProcessing
@ComponentScan(basePackages = "org.example")
public class SchedulerJobFactoryConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public JobFactory jobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
}
