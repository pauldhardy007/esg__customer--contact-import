package org.winharleigh.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

class RetrievedFileContentsTest {
    private RetrievedFileContents retrievedFileContents;

    @BeforeEach
    void setUp() {
        retrievedFileContents = new RetrievedFileContents();
    }

    @Test
    void shouldReturnScannerContainingFileContentGivenFilePath() {
        String filePath = requireNonNull(this.retrievedFileContents.getClass()
                .getResource("/csv/customer_contact.csv"))
                .getPath();
        String expected = "\"ref001\",\"Fred Blogs\",\"1 Some Road\",\"Some Area\",\"Townton\",\"Warwickshire\",\"England\",\"WE12 0DF\"";
        Scanner scanner = retrievedFileContents.getScanner(filePath);
        assertNotNull(scanner);
        assertEquals(expected, scanner.nextLine());
    }
}