package org.winharleigh.exercise.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.winharleigh.exercise.client.CustomerAdaptorClient;
import org.winharleigh.exercise.dto.CustomerDto;
import org.winharleigh.exercise.service.ImportFromCsvFile;
import org.winharleigh.exercise.service.RetrievedFileContents;
import org.winharleigh.exercise.transformer.ConvertCsvRecordToCustomer;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.winharleigh.exercise.util.TestHelper.*;

@ExtendWith(MockitoExtension.class)
public class ImportFromCsvFileIT {

    private ImportFromCsvFile importCsv;
    private RetrievedFileContents retrievedFileContents;
    private ConvertCsvRecordToCustomer transformer;
    private ObjectMapper objectMapper;
    @Mock
    private CustomerAdaptorClient customerAdaptorClient;
    private String resourceDirectory;

    @BeforeEach
    void setUp() {
        retrievedFileContents = new RetrievedFileContents();
        transformer = new ConvertCsvRecordToCustomer();
        objectMapper = new ObjectMapper();
        importCsv = new ImportFromCsvFile(retrievedFileContents, transformer, objectMapper, customerAdaptorClient);
    }

    @Test
    void shouldCallSaveToDbPostRestWhenJsonSuccessfullyAbstractedFromCsv() throws FileNotFoundException {
        doNothing().when(customerAdaptorClient).sendCustomerContactDetail(any());
        String csvFilePath = getResourceFilePath("/csv/customer_contact.csv");

        importCsv.process(csvFilePath);

        verify(customerAdaptorClient).sendCustomerContactDetail(any());
    }

    private String getExpectedJson(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
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

    private String getCustomerAsString() {
        return "\"ref001\", \"Fred Blogs\", \"1 Some Road\", \"Some Area\", \"Townton\", \"Warwickshire\", " +
                "\"England\",\"WE12 0DF\"";
    }
}