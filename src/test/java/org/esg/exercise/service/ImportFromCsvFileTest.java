package org.esg.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.esg.exercise.client.CustomerAdaptorClient;
import org.esg.exercise.dto.CustomerDto;
import org.esg.exercise.transformer.ConvertCsvRecordToCustomer;
import org.esg.exercise.util.TestHelper;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.esg.exercise.util.TestHelper.*;

@ExtendWith(MockitoExtension.class)
public class ImportFromCsvFileTest {

    private ImportFromCsvFile importCsv;
    @Mock
    private RetrievedFileContents retrievedFileContents;
    @Mock
    private ConvertCsvRecordToCustomer transformer;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private CustomerAdaptorClient customerAdaptorClient;
    private String resourceDirectory;

    private TestHelper testHelper;

    @BeforeEach
    void setUp() {
        importCsv = new ImportFromCsvFile(retrievedFileContents, transformer, objectMapper, customerAdaptorClient);
        testHelper = new TestHelper();
    }

    @Test
    void shouldExtractDataFromCsvAndPostToSaveToDbRestCallWhenNoExceptionOccur() throws JsonProcessingException,
            FileNotFoundException {
        String csvFilePath = testHelper.getResourceFilePath("/csv/customer_contact.csv");
        Scanner scanner = getScanner(csvFilePath);
        String customerAsString = getCustomerAsString();
        CustomerDto customer = getCustomerDto();
        String json = getExpectedJson("/json/customer_content.json");

        when(retrievedFileContents.getScanner(csvFilePath)).thenReturn(scanner);
        when(transformer.transform(customerAsString)).thenReturn(customer);
        when(objectMapper.writeValueAsString(customer)).thenReturn(json);
        doNothing().when(customerAdaptorClient)
                .sendCustomerContactDetail(json);

        importCsv.process(csvFilePath);

        verify(retrievedFileContents).getScanner(any());
        verify(transformer).transform(any());
        verify(objectMapper).writeValueAsString(any());
        verify(customerAdaptorClient).sendCustomerContactDetail(any());
    }

    private CustomerDto getCustomerDto() {
        return CustomerDto.builder()
                .customerRef(CUSTOMER_REF)
                .customerName(CUSTOMER_NAME)
                .addressLineOne(ADDRESS_LINE_ONE)
                .addressLineTwo(ADDRESS_LINE_TWO)
                .town(TOWN)
                .county(COUNTY)
                .country(COUNTRY)
                .postcode(POSTCODE)
                .build();
    }
}