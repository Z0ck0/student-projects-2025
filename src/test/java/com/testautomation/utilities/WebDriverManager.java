package com.testautomation.utilities;

import com.testautomation.enums.BrowserType;
import com.testautomation.exceptions.WebDriverException;
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
            if (browserName == null || browserName.trim().isEmpty()) {
                throw new WebDriverException("Browser name cannot be null or empty");
            }
            
            BrowserType browserType = BrowserType.fromString(browserName);
            driver = createDriver(browserType);
            LoggerUtil.info("WebDriver initialized successfully for browser: " + browserName);
            return driver;
        } catch (WebDriverException e) {
            // Re-throw WebDriverException as-is
            throw e;
        } catch (Exception e) {
            LoggerUtil.error("Failed to initialize WebDriver for browser: " + browserName, e);
            throw new WebDriverException("WebDriver", 
                "Failed to initialize WebDriver for browser: " + browserName, e);
        }
    }

    private WebDriver createDriver(BrowserType browserType) {
        try {
            switch (browserType) {
                case CHROME:
                    return createChromeDriver();
                case FIREFOX:
                    return createFirefoxDriver();
                case EDGE:
                    return createEdgeDriver();
                case SAFARI:
                    return createSafariDriver();
                default:
                    throw new WebDriverException("Unsupported browser type: " + browserType);
            }
        } catch (Exception e) {
            throw new WebDriverException("WebDriver", 
                "Failed to create driver for browser type: " + browserType, e);
        }
    }

    private WebDriver createChromeDriver() {
        try {
            ChromeDriverService chromeService = ChromeDriverService.createDefaultService();
            return new ChromeDriver(chromeService, getChromeOptions());
        } catch (Exception e) {
            throw new WebDriverException("ChromeDriver", 
                "Failed to create Chrome driver", e);
        }
    }

    private WebDriver createFirefoxDriver() {
        try {
            GeckoDriverService firefoxService = GeckoDriverService.createDefaultService();
            return new FirefoxDriver(firefoxService, getFirefoxOptions());
        } catch (Exception e) {
            throw new WebDriverException("FirefoxDriver", 
                "Failed to create Firefox driver", e);
        }
    }

    private WebDriver createEdgeDriver() {
        try {
            EdgeDriverService edgeService = EdgeDriverService.createDefaultService();
            return new EdgeDriver(edgeService, getEdgeOptions());
        } catch (Exception e) {
            throw new WebDriverException("EdgeDriver", 
                "Failed to create Edge driver", e);
        }
    }

    private WebDriver createSafariDriver() {
        try {
            return new SafariDriver(getSafariOptions());
        } catch (Exception e) {
            throw new WebDriverException("SafariDriver", 
                "Failed to create Safari driver", e);
        }
    }

    private ChromeOptions getChromeOptions() {
        try {
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
        } catch (Exception e) {
            throw new WebDriverException("ChromeOptions", 
                "Failed to create Chrome options", e);
        }
    }

    private FirefoxOptions getFirefoxOptions() {
        try {
            FirefoxOptions options = new FirefoxOptions();
            if (ConfigReader.isHeadless()) {
                options.addArguments("--headless");
            }
            return options;
        } catch (Exception e) {
            throw new WebDriverException("FirefoxOptions", 
                "Failed to create Firefox options", e);
        }
    }

    private EdgeOptions getEdgeOptions() {
        try {
            EdgeOptions options = new EdgeOptions();
            if (ConfigReader.isHeadless()) {
                options.addArguments("--headless");
            }
            return options;
        } catch (Exception e) {
            throw new WebDriverException("EdgeOptions", 
                "Failed to create Edge options", e);
        }
    }

    private SafariOptions getSafariOptions() {
        try {
            SafariOptions options = new SafariOptions();
            return options;
        } catch (Exception e) {
            throw new WebDriverException("SafariOptions", 
                "Failed to create Safari options", e);
        }
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
                LoggerUtil.info("WebDriver quit successfully");
            } catch (Exception e) {
                LoggerUtil.error("Failed to quit WebDriver", e);
                throw new WebDriverException("WebDriver", 
                    "Failed to quit WebDriver", e);
            }
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            throw new WebDriverException("WebDriver", 
                "WebDriver is not initialized. Call initiateDriver() first.");
        }
        return driver;
    }

    /**
     * Check if WebDriver is initialized
     */
    public boolean isDriverInitialized() {
        return driver != null;
    }

    /**
     * Get current browser name
     */
    public String getCurrentBrowserName() {
        if (driver == null) {
            return "Not initialized";
        }
        return driver.getClass().getSimpleName().replace("Driver", "");
    }
}
