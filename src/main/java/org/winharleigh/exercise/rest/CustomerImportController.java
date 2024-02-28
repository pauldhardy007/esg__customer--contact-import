package org.winharleigh.exercise.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.winharleigh.exercise.service.ImportFromCsvFile;

@RequestMapping("/customers")
@RestController
@AllArgsConstructor
public class CustomerImportController {
    private ImportFromCsvFile importFromCsvFile;

    @PostMapping(value = "/contacts/import")
    public void importCustomerContactDetail(@RequestParam(value = "filepath", required = true) final String filePath) {
        System.out.println("HERE!!" + filePath);
        importFromCsvFile.process(filePath);
    }
}
