package com.demoqa.utilities;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    // Define ANSI escape codes for colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";

    static {
        // Remove existing handlers to avoid duplicate log messages
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        // Create a new console handler and set the custom formatter
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFormatter());
        consoleHandler.setLevel(Level.ALL);

        // Add the new console handler to the logger
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
    }

    // Log an informational message in green
    public static void info(String message) {
        log(Level.INFO, message, ANSI_GREEN);
    }

    // Log a warning message in yellow
    public static void warning(String message) {
        log(Level.WARNING, message, ANSI_YELLOW);
    }

    // Log an error message in red along with a throwable
    public static void error(String message, Throwable throwable) {
        LogRecord record = new LogRecord(Level.SEVERE, message);
        record.setThrown(throwable);
        logRecord(record, ANSI_RED);
    }

    // Common method to log a message with a specific color
    private static void log(Level level, String message, String color) {
        LogRecord record = new LogRecord(level, color + message + ANSI_RESET);
        logRecord(record, color);
    }

    // Common method to log a LogRecord with a specific color
    private static void logRecord(LogRecord record, String color) {
        String formattedMessage = new CustomFormatter().format(record);
        logger.log(record.getLevel(), formattedMessage);
    }

    // Custom formatter to include timestamps and thread information
    private static class CustomFormatter extends Formatter {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @NotNull
        @Override
        public String format(@NotNull LogRecord record) {
            StringBuilder builder = new StringBuilder();
            builder.append(dateFormat.format(new Date(record.getMillis())))
                    .append(" [").append(record.getLevel()).append("] ")
                    .append("[").append(Thread.currentThread().getName()).append("] ");

            String message = formatMessage(record);
            if (message != null) {
                builder.append(message);
            }

            builder.append(System.lineSeparator());
            return builder.toString();
        }
    }
}
