package org.esg.exercise.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.esg.exercise.exception.CustomerAdaptorUnavailableException;
import org.esg.exercise.util.TestHelper;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerAdaptorClientTest {

    private static final String CUSTOMERS_CONTACT_SAVE_URI = "/customers/contact/save";
    private CustomerAdaptorClient customerAdaptorClient;
    @Mock
    private RestTemplate customerAdaptorRestTemplate;

    @BeforeEach
    void setUp() {
        customerAdaptorClient = new CustomerAdaptorClient(customerAdaptorRestTemplate);
    }

    @Test
    void shouldSendCustomerContactDetailToCustomerClient() throws FileNotFoundException {
        String json = TestHelper.getExpectedJson("/json/customer_content.json");
        when(customerAdaptorRestTemplate.postForEntity(CUSTOMERS_CONTACT_SAVE_URI, json, Void.class)).thenReturn(null);

        customerAdaptorClient.sendCustomerContactDetail(json);

        verify(customerAdaptorRestTemplate).postForEntity(CUSTOMERS_CONTACT_SAVE_URI, json, Void.class);
    }

    @Test
    void shouldThrowCustomerAdaptorUnavailableExceptionWhenPostEntityExceptionOccurs() throws FileNotFoundException {
        String json = TestHelper.getExpectedJson("/json/customer_content.json");
        doThrow(RestClientException.class).when(customerAdaptorRestTemplate).postForEntity(CUSTOMERS_CONTACT_SAVE_URI, json, Void.class);

        CustomerAdaptorUnavailableException thrown = assertThrows(
                CustomerAdaptorUnavailableException.class,
                () -> customerAdaptorClient.sendCustomerContactDetail(json));

        verify(customerAdaptorRestTemplate).postForEntity(CUSTOMERS_CONTACT_SAVE_URI, json, Void.class);
    }
}