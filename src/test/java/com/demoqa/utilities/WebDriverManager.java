package com.demoqa.utilities;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {
    private WebDriver driver;

    public WebDriver initiateDriver(@NotNull String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-popup-blocking", "--incognito", "--disable-logging");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized", "--disable-popup-blocking", "--inprivate");
            driver = new EdgeDriver(edgeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--start-maximized", "--disable-popup-blocking", "-private");
            driver = new FirefoxDriver(firefoxOptions);
        } else {
            System.out.println("Invalid browser name provided");
        }
        return driver;
    }
}
