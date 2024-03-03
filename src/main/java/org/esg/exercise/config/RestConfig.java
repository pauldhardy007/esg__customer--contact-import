package org.esg.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate customerAdaptorRestTemplate(
            @Value("${customer.contact.adaptor.baseurl}") final String customerAdaptorBaseUrl) {
        return new RestTemplateBuilder().rootUri(customerAdaptorBaseUrl)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }
}
