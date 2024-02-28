package org.winharleigh.customer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.winharleigh.customer.dto.CustomerDto;
import org.winharleigh.exercise.transformer.ConvertCsvRecordToCustomer;
import org.winharleigh.exercise.service.ImportFromCsvFile;
import org.winharleigh.exercise.service.RetrievedFileContents;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.winharleigh.customer.util.TestHelper.*;

@ExtendWith(MockitoExtension.class)
public class ImportFromCsvFileTest {

    private ImportFromCsvFile importCsv;
    @Mock
    private RetrievedFileContents retrievedFileContents;
    @Mock
    private ConvertCsvRecordToCustomer transformer;
    @Mock
    private ObjectMapper objectMapper;
    private String resourceDirectory;

    @BeforeEach
    void setUp() {
        importCsv = new ImportFromCsvFile(retrievedFileContents, transformer, objectMapper);
    }

    @Test
    void process() throws JsonProcessingException, FileNotFoundException {
        String csvFilePath = getResourceFilePath("/csv/customer_contact.csv");
        Scanner scanner = getScanner(csvFilePath);
        String customerAsString = getCustomerAsString();
        CustomerDto customer = getCustomerDto();
        String json = getExpectedJson(getResourceFilePath("/json/customer_content.json"));

        when(retrievedFileContents.getScanner(csvFilePath)).thenReturn(scanner);
        when(transformer.transform(customerAsString)).thenReturn(customer);
        when(objectMapper.writeValueAsString(customer)).thenReturn(json);

        importCsv.process(csvFilePath);

        verify(retrievedFileContents).getScanner(any());
        verify(transformer).transform(any());
        verify(objectMapper).writeValueAsString(any());
    }

    private String getExpectedJson(String jsonFilePath) throws FileNotFoundException {
        Scanner scanner = getScanner(jsonFilePath);
        String json = "";
        while (scanner.hasNextLine()) {
            json = json.concat(scanner.nextLine())
                    .replace(" ", "");
        }
        return json;
    }

    private Scanner getScanner(String filePath) throws FileNotFoundException {
        return new Scanner(new File(filePath));
    }

    private String getResourceFilePath(final String filePath) {
        return requireNonNull(this.importCsv.getClass()
                .getResource(filePath))
                .getPath();
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