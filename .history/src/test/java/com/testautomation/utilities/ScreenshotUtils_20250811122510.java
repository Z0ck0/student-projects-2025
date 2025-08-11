package com.testautomation.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    
    private static final String SCREENSHOT_DIR = "screenshots";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    
    /**
     * Take a screenshot and save it to the screenshots directory
     * @param driver the WebDriver instance
     * @param testName the name of the test for the screenshot filename
     * @return the path to the saved screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            createScreenshotDirectory();
            
            // Generate filename with timestamp
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            String filename = String.format("%s_%s.png", testName, timestamp);
            Path filePath = Paths.get(SCREENSHOT_DIR, filename);
            
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);
            
            // Save screenshot to file
            Files.write(filePath, screenshotBytes);
            
            LoggerUtil.info("Screenshot saved to: " + filePath.toAbsolutePath());
            return filePath.toString();
            
        } catch (Exception e) {
            LoggerUtil.error("Failed to take screenshot: " + e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Take a screenshot and return it as bytes
     * @param driver the WebDriver instance
     * @return screenshot as byte array
     */
    public static byte[] takeScreenshotAsBytes(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            LoggerUtil.error("Failed to take screenshot as bytes: " + e.getMessage(), e);
            return new byte[0];
        }
    }
    
    /**
     * Create the screenshots directory if it doesn't exist
     */
    private static void createScreenshotDirectory() {
        try {
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotPath)) {
                Files.createDirectories(screenshotPath);
                LoggerUtil.info("Created screenshots directory: " + screenshotPath.toAbsolutePath());
            }
        } catch (IOException e) {
            LoggerUtil.error("Failed to create screenshots directory: " + e.getMessage(), e);
        }
    }
    
    /**
     * Clean up old screenshots (older than specified days)
     * @param daysToKeep number of days to keep screenshots
     */
    public static void cleanupOldScreenshots(int daysToKeep) {
        try {
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotPath)) {
                return;
            }
            
            long cutoffTime = System.currentTimeMillis() - (daysToKeep * 24L * 60L * 60L * 1000L);
            
            Files.list(screenshotPath)
                .filter(path -> path.toString().endsWith(".png"))
                .filter(path -> {
                    try {
                        return Files.getLastModifiedTime(path).toMillis() < cutoffTime;
                    } catch (IOException e) {
                        return false;
                    }
                })
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        LoggerUtil.info("Deleted old screenshot: " + path.getFileName());
                    } catch (IOException e) {
                        LoggerUtil.warning("Failed to delete old screenshot: " + path.getFileName());
                    }
                });
                
        } catch (IOException e) {
            LoggerUtil.error("Failed to cleanup old screenshots: " + e.getMessage(), e);
        }
    }
}
