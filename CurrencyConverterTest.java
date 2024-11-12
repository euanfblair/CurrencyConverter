import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CurrencyConverterTest {

    // Task 5a: Test for missing or invalid input arguments
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
        String[] args = {"invalid", "dollars"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CurrencyConverter.main(args);

        String expectedOutput = "Invalid amount provided.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    // Task 5b: Test for case sensitivity in currency input
    @Test
    public void testCurrencyCaseSensitivity() {
        String[] args1 = {"100", "DOLLARS"};
        String[] args2 = {"100", "Dollars"};
        String[] args3 = {"100", "dollars"};

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

    // Task 5c: Test for correct conversion calculations

    @Test
    public void testDollarToPoundAndEuroConversion() {
        String[] args = {"1", "dollars"};
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

