package com.demoqa.utilities;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    public static final Logger logger = Logger.getLogger(CustomLogger.class.getName());

    static {
        // Configure the logger with a ConsoleHandler and set the logging level
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
    }


    // Log an informational message
    public static void info(String message) {
        logger.info(message);
    }


    // Log a warning message
    public static void warning(String message) {
        logger.warning(message);
    }


    // Log an error message along with a throwable
    public static void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

}
