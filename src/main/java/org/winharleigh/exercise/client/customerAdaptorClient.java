package org.winharleigh.exercise.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.winharleigh.exercise.dto.CustomerDto;
import org.winharleigh.exercise.exception.CustomerAdaptorUnavailableException;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@Slf4j
@RequiredArgsConstructor
public class customerAdaptorClient {

    private static final String GET_CUSTOMER_CONTACT_URI = "/customers/%s";
    private final RestTemplate customerAdaptorRestTemplate;

    public CustomerDto getCustomer(final String accountNumber) {
        try {
            return requireNonNull(customerAdaptorRestTemplate
                    .getForObject(format(GET_CUSTOMER_CONTACT_URI, accountNumber), CustomerDto.class));
        } catch (final Exception e) {
            throw new CustomerAdaptorUnavailableException(e.getMessage(), "Getting Customer");
        }
    }
}
