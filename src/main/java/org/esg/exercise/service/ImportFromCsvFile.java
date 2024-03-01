package org.esg.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.esg.exercise.client.CustomerAdaptorClient;
import org.esg.exercise.dto.CustomerDto;
import org.esg.exercise.transformer.ConvertCsvRecordToCustomer;

import java.util.Scanner;

@AllArgsConstructor
@Service
public class ImportFromCsvFile {

    private RetrievedFileContents retrievedFileContents;
    private ConvertCsvRecordToCustomer transformer;
    private final ObjectMapper objectMapper;
    private CustomerAdaptorClient customerAdaptorClient;

    public void process(final String filePath) {
        try (Scanner scanner = retrievedFileContents.getScanner(filePath)) {
            while (scanner.hasNextLine()) {
                CustomerDto customer = transformer.transform(scanner.nextLine());
                String json = objectMapper.writeValueAsString(customer);
                System.out.println(json);
                customerAdaptorClient.sendCustomerContactDetail(json);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
