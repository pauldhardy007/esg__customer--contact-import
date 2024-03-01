package org.winharleigh.exercise.service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class StringCalculator {

    public static final String NUMBERS = "0123456789";

    public int add(String numbers) throws Exception {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> delimiterList = new ArrayList<>();
        String delimiters = "";
        if (numbers.startsWith("//")) {
            delimiters = numbers.substring(2, numbers.indexOf("\n"));
            if (delimiters.startsWith("[")) {
                String ds = delimiters;
                while (!ds.isEmpty()) {
                    String d = ds.substring(1,ds.indexOf("]"));
                    delimiterList.add(d);
                    ds = ds.replace("[" + d + "]", "");
                }
            } else {
                delimiterList.add(delimiters);
            }
        }
        numbers = numbers.replace("//" + delimiters + "\n", "");

        for (String d : delimiterList) {
            numbers = numbers.replace(d,",");
        }

        int total = 0;
        String delimiter = ",";
        numbers = numbers.replace("\n", delimiter);
        String[] numbersArr = numbers.split(delimiter);
        total += getTotal(numbersArr);
        return total;
    }

    private static int getTotal(String[] numbersArr) throws Exception {
        int total = 0;
        StringBuilder negativeNumbers = new StringBuilder();
        for (String s : numbersArr) {
            try {
                int i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                continue;
            }
            int i = Integer.parseInt(s);
            if (i < 0) {
                negativeNumbers.append(",").append(i);
            } else if (i > 1000) {
                continue;
            }
            total += i;
        }
        if (negativeNumbers.isEmpty()) {
            return total;
        }
        throw new Exception(format("Negatives not allowed: %s", negativeNumbers.deleteCharAt(0)));
    }
}
