package org.winharleigh.customer.util;

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
}
