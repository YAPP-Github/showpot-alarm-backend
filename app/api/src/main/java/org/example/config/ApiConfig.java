package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({UserApiConfig.class, ShowApiConfig.class})
public class ApiConfig {

}
