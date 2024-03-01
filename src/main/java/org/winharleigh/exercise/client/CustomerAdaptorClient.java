package org.winharleigh.exercise.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.winharleigh.exercise.dto.CustomerDto;
import org.winharleigh.exercise.exception.CustomerAdaptorUnavailableException;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomerAdaptorClient {

    private static final String CUSTOMERS_CONTACT_SAVE_URI = "/customers/contact/save";
    private final RestTemplate customerAdaptorRestTemplate;

    public void sendCustomerContactDetail(final String payload) {
        try {
            System.out.println("HERE sendCustomerContactDetail - IN");
            customerAdaptorRestTemplate
                    .postForEntity(CUSTOMERS_CONTACT_SAVE_URI, payload, Void.class);
            System.out.println("HERE sendCustomerContactDetail - OUT");
        } catch (final RestClientException e) {
            throw new CustomerAdaptorUnavailableException(e.getMessage(), "Getting Customer");
        }
    }
}
