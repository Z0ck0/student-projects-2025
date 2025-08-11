package com.testautomation.utilities;

import com.testautomation.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WebDriverManager {
    private WebDriver driver;

    public WebDriver initiateDriver(String browserName) {
        try {
            BrowserType browserType = BrowserType.fromString(browserName);
            driver = createDriver(browserType);
            LoggerUtil.info("WebDriver initialized successfully for browser: " + browserName);
            return driver;
        } catch (Exception e) {
            LoggerUtil.error("Failed to initialize WebDriver for browser: " + browserName, e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    private WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                ChromeDriverService chromeService = ChromeDriverService.createDefaultService();
                return new ChromeDriver(chromeService, getChromeOptions());
            case FIREFOX:
                GeckoDriverService firefoxService = GeckoDriverService.createDefaultService();
                return new FirefoxDriver(firefoxService, getFirefoxOptions());
            case EDGE:
                EdgeDriverService edgeService = EdgeDriverService.createDefaultService();
                return new EdgeDriver(edgeService, getEdgeOptions());
            case SAFARI:
                return new SafariDriver(getSafariOptions());
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
            "--start-maximized",
            "--disable-popup-blocking",
            "--disable-logging",
            "--disable-extensions",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--disable-gpu",
            "--disable-web-security",
            "--allow-running-insecure-content",
            "--disable-features=VizDisplayCompositor",
            "--remote-debugging-port=9222"
        );
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
        }
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
        }
        return options;
    }

    private SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        return options;
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                LoggerUtil.info("WebDriver quit successfully");
            } catch (Exception e) {
                LoggerUtil.error("Failed to quit WebDriver", e);
            }
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
