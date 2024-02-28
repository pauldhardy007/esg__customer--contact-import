package org.winharleigh.exercise.transformer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.winharleigh.exercise.dto.CustomerDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertCsvRecordToCustomerTest {
    private ConvertCsvRecordToCustomer transformer;

    @BeforeEach
    void setUp() {
        transformer = new ConvertCsvRecordToCustomer();
    }

    @Test
    void transform() {
        CustomerDto expected = CustomerDto.builder()
                .customerRef("ref001")
                .customerName("Fred Blogs")
                .addressLineOne("1 Some Road")
                .addressLineTwo("Some Area")
                .town("Townton")
                .county("Warwickshire")
                .country("England")
                .postcode("WE12 0DF")
                .build();
        String record = "\"ref001\",\"Fred Blogs\",\"1 Some Road\",\"Some Area\",\"Townton\",\"Warwickshire\",\"England\",\"WE12 0DF\"";
        CustomerDto actual = transformer.transform(record);

        assertEquals(expected, actual);
    }
}