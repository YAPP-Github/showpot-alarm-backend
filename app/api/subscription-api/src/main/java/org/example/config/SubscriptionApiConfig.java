package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SubscriptionDomainConfig.class)
@ComponentScan(basePackages = "org.example")
public class SubscriptionApiConfig {

}
