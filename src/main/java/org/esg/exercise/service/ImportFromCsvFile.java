package org.esg.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.esg.exercise.client.CustomerAdaptorClient;
import org.esg.exercise.dto.CustomerDto;
import org.esg.exercise.transformer.ConvertCsvRecordToCustomer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@AllArgsConstructor
@Service
@Slf4j
public class ImportFromCsvFile {

    private RetrievedFileContents retrievedFileContents;
    private ConvertCsvRecordToCustomer transformer;
    private ObjectMapper objectMapper;
    private CustomerAdaptorClient customerAdaptorClient;

    public void process(final String filePath) {
        try (Scanner scanner = retrievedFileContents.getScanner(filePath)) {
            while (scanner.hasNextLine()) {
                CustomerDto customer = transformer.transform(scanner.nextLine());
                String json = objectMapper.writeValueAsString(customer);
                log.info("ImportFromCsvFile.process({}) json[{}]", filePath, json);
                customerAdaptorClient.sendCustomerContactDetail(json);
            }
        } catch (JsonProcessingException e) {
            log.info("Failed to process csv file. ERROR[{}] CAUSE[{}]", e.getMessage(), e.getCause()
                    .getMessage());
            throw new RuntimeException(e);
        }
    }
}
