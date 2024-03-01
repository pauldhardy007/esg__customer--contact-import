package org.esg.exercise.rest;

import org.esg.exercise.service.ImportFromCsvFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerAdaptorControllerTest {
    public static final String FILE_PATH = "filepath";
    private CustomerImportController customerAdaptorController;
    @Mock
    private ImportFromCsvFile importFromCsvFile;

    @BeforeEach
    void setUp() {
        customerAdaptorController = new CustomerImportController(importFromCsvFile);
    }

    @Test
    void importCustomerContactDetail() {
        doNothing().when(importFromCsvFile).process(FILE_PATH);

        customerAdaptorController.importCustomerContactDetail(FILE_PATH);

        verify(importFromCsvFile).process(FILE_PATH);
    }
}