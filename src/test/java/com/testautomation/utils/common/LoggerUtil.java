package com.testautomation.utils.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info(timestamp + " [INFO] " + message);
    }

    public static void warning(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.warning(timestamp + " [WARNING] " + message);
    }

    public static void error(String message, Throwable throwable) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.log(Level.SEVERE, timestamp + " [ERROR] " + message, throwable);
    }

    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.severe(timestamp + " [ERROR] " + message);
    }

    public static void debug(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.fine(timestamp + " [DEBUG] " + message);
    }
}
