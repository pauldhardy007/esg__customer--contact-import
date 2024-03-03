package org.esg.exercise.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.esg.exercise.service.ImportFromCsvFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customers")
@RestController
@AllArgsConstructor
@Slf4j
public class CustomerImportController {
    private ImportFromCsvFile importFromCsvFile;

    @PostMapping(value = "/contacts/import")
    public void importCustomerContactDetail(@RequestParam(value = "filepath", required = true) final String filePath) {
        log.info("Importing Customer Contact Detail. filePath[{}]", filePath);
        importFromCsvFile.process(filePath);
    }
}
