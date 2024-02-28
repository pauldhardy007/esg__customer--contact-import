package org.winharleigh.exercise.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

public class TestHelper {

    public static final String CUSTOMER_REF = "ref001";
    public static final String CUSTOMER_NAME = "Fred Blogs";
    public static final String ADDRESS_LINE_ONE = "1 Some Road";
    public static final String ADDRESS_LINE_TWO = "Some Area";
    public static final String TOWN = "Townton";
    public static final String COUNTY = "Warwickshire";
    public static final String COUNTRY = "England";
    public static final String POSTCODE = "WE12 0DF";

    public static String getCustomerAsString() {
        return "\"ref001\",\"Fred Blogs\",\"1 Some Road\",\"Some Area\",\"Townton\",\"Warwickshire\"," +
                "\"England\",\"WE12 0DF\"";
    }

    public static String getExpectedJson(String jsonFilePath) throws FileNotFoundException {
        String filePath = new TestHelper().getResourceFilePath(jsonFilePath);
        Scanner scanner = getScanner(filePath);
        String json = "";
        while (scanner.hasNextLine()) {
            json = json.concat(scanner.nextLine())
                    .replace(" ", "");
        }
        return json;
    }

    public static Scanner getScanner(String filePath) throws FileNotFoundException {
        return new Scanner(new File(filePath));
    }

    public String getResourceFilePath(final String filePath) {
        return requireNonNull(this.getClass()
                .getResource(filePath))
                .getPath();
    }

}
