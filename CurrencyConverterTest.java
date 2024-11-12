package com.example.currencyconverter;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CurrencyConverterTest {

    // Constants for test values
    private static final String AMOUNT = "100";
    private static final String DOLLARS_UPPER = "DOLLARS";
    private static final String DOLLARS_CAPITAL = "Dollars";
    private static final String DOLLARS_LOWER = "dollars";

    @Test
    public void testNoArgumentsProvided() {
        String[] args = {};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CurrencyConverter.main(args);

        String expectedOutput = "Please provide amount and currency type.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testInvalidAmountProvided() {
        String[] args = {"invalid", DOLLARS_LOWER};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CurrencyConverter.main(args);

        String expectedOutput = "Invalid amount provided.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testCurrencyCaseSensitivity() {
        String[] args1 = {AMOUNT, DOLLARS_UPPER};
        String[] args2 = {AMOUNT, DOLLARS_CAPITAL};
        String[] args3 = {AMOUNT, DOLLARS_LOWER};

        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent1));
        CurrencyConverter.main(args1);
        String output1 = outContent1.toString();

        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        CurrencyConverter.main(args2);
        String output2 = outContent2.toString();

        ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent3));
        CurrencyConverter.main(args3);
        String output3 = outContent3.toString();

        // The outputs should be the same for different cases
        assertEquals(output1, output2);
        assertEquals(output2, output3);
    }

    @Test
    public void testDollarToPoundAndEuroConversion() {
        String[] args = {"1", DOLLARS_LOWER};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CurrencyConverter.main(args);

        String expectedOutput = "1.0 Dollars = 0.74 Pounds\n" +
                                "1.0 Dollars = 0.88 Euros\n" +
                                "Thank you for using the converter.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPoundToDollarAndEuroConversion() {
        String[] args = {"1", "pounds"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CurrencyConverter.main(args);

        String expectedOutput = "1.0 Pounds = 1.36 Dollars\n" +
                                "1.0 Pounds = 1.19 Euros\n" +
                                "Thank you for using the converter.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testEuroToDollarAndPoundConversion() {
        String[] args = {"1", "euros"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CurrencyConverter.main(args);

        String expectedOutput = "1.0 Euros = 1.13 Dollars\n" +
                                "1.0 Euros = 0.84 Pounds\n" +
                                "Thank you for using the converter.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}

