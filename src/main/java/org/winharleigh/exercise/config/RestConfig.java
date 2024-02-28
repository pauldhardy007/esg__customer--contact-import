package org.winharleigh.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate customerAdaptorRestTemplate(
            @Value("${customer.contact.adaptor.baseurl}") final String customerAdaptorBaseUrl) {
        return new RestTemplateBuilder().rootUri(customerAdaptorBaseUrl)
                .build();
    }
}
