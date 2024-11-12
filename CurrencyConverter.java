package com.example.currencyconverter;

import java.util.*;
import java.text.DecimalFormat;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class CurrencyConverter {

    // Logger initialization
    private static final Logger logger = Logger.getLogger(CurrencyConverter.class.getName());

    // Constants for currency strings
    private static final String CURRENCY_DOLLARS = "dollars";
    private static final String CURRENCY_POUNDS = "pounds";
    private static final String CURRENCY_EUROS = "euros";

    static {
        // Configure logger to output to System.out
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers.length > 0 && handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(java.util.logging.LogRecord lr) {
                return lr.getMessage() + System.lineSeparator();
            }
        });
        handler.setOutputStream(System.out);
        rootLogger.addHandler(handler);
        rootLogger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {

        // Input Validation: Check if sufficient arguments are provided
        if (args.length < 2) {
            logger.info("Please provide amount and currency type.");
            return;
        }

        double amount;
        try {
            // Input Validation: Try parsing the amount to a double
            amount = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            logger.info("Invalid amount provided.");
            return;
        }

        // Case Insensitivity: Convert currency input to lowercase
        String currency = args[1].toLowerCase();

        double dollar;
        double pound;
        double euro;
        DecimalFormat f = new DecimalFormat("##.##");

        // Correct Conversion Rates and Handling Unrecognized Currency
        switch (currency){
            case CURRENCY_DOLLARS:
                // For Dollar Conversion
                pound = amount * 0.74;
                logger.info(amount + " Dollars = " + f.format(pound) + " Pounds");
                euro = amount * 0.88;
                logger.info(amount + " Dollars = " + f.format(euro) + " Euros");
                break;
            case CURRENCY_POUNDS:
                // For Pound Conversion
                dollar = amount * 1.36;
                logger.info(amount + " Pounds = " + f.format(dollar) + " Dollars");
                euro = amount * 1.19;
                logger.info(amount + " Pounds = " + f.format(euro) + " Euros");
                break;
            case CURRENCY_EUROS:
                // For Euro Conversion
                dollar = amount * 1.13;
                logger.info(amount + " Euros = " + f.format(dollar) + " Dollars");
                pound = amount * 0.84;
                logger.info(amount + " Euros = " + f.format(pound) + " Pounds");
                break;
            default:
                logger.info("Currency not recognized.");
                return;
        }
        logger.info("Thank you for using the converter.");
    }
}

