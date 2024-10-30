package dev.syed.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Applicationconfiguration {
    @Bean
    public RestTemplate createrestTemplate() {
        return new RestTemplate();
    }
}
