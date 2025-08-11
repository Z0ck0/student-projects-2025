# Framework Usage Guide

Comprehensive guide for using the Student Projects 2025 test automation framework, including patterns, best practices, examples, and technical details.

## Table of Contents

1. [Framework Architecture](#framework-architecture)
2. [Creating Tests](#creating-tests)
3. [Page Object Model](#page-object-model)
4. [Framework Features](#framework-features)
5. [Best Practices](#best-practices)
6. [Examples and Templates](#examples-and-templates)

## Framework Architecture

### Package Structure

The framework follows a **logical, scalable package structure** that separates concerns and makes the codebase easy to navigate:

```
src/test/java/com/testautomation/
├── core/                          # 🎯 Core framework components
│   ├── config/                    # Configuration management
│   ├── driver/                    # WebDriver lifecycle management
│   ├── exceptions/                # Custom exception hierarchy
│   └── listeners/                 # TestNG event listeners
├── enums/                         # 📊 Framework constants and enums
├── fixtures/                      # 🗃️ Test data providers
├── pages/                         # 🖥️ Page Object Model classes
├── tests/                         # 🧪 Test execution classes
│   ├── base/                      # Base test infrastructure
│   ├── examples/                  # Example test implementations
│   └── suites/                    # Test suite configurations
└── utils/                         # 🛠️ Utility and helper classes
    ├── browser/                   # Browser-specific utilities
    ├── common/                    # Common framework utilities
    ├── data/                      # Data management utilities
    └── reporting/                 # Reporting and logging utilities
```

### Core Components

#### Configuration Management (`core.config`)

- **ConfigReader**: Centralized configuration management
- **Purpose**: Single source of truth for all framework settings
- **Benefits**: Environment independence, easy maintenance, centralized validation

#### WebDriver Management (`core.driver`)

- **WebDriverManager**: Browser lifecycle management
- **Purpose**: Centralized browser initialization and cleanup
- **Benefits**: Consistent setup, conflict prevention, proper resource management

#### Exception Handling (`core.exceptions`)

- **FrameworkException**: Base exception for all framework errors
- **TestSetupException**: Test lifecycle errors
- **WebDriverException**: Browser automation errors
- **ConfigurationException**: Configuration-related errors

#### TestNG Integration (`core.listeners`)

- **TestListener**: Comprehensive test execution tracking
- **ExceptionTestListener**: Centralized exception handling
- **RetryAnalyzer**: Automatic test retry mechanism

### Utility Classes

#### Browser Utilities (`utils.browser`)

- **ScreenshotUtils**: Screenshot capture and management
- **WaitUtils**: Explicit wait utilities for elements

#### Common Utilities (`utils.common`)

- **LoggerUtil**: Structured logging with different levels

#### Data Utilities (`utils.data`)

- **RandomDataGenerator**: Realistic test data generation

## Creating Tests

### Test Class Structure

All test classes should extend `BaseTest` to inherit common setup and teardown:

```java
package com.testautomation.tests.examples;

import com.testautomation.tests.base.BaseTest;
import org.testng.annotations.Test;

public class MyTest extends BaseTest {

    @Test(description = "Test description")
    public void testMethod() {
        // Test implementation
    }
}
```

### Test Method Annotations

Use TestNG annotations for test configuration:

```java
@Test(
    description = "Verify user can login with valid credentials",
    groups = {"smoke", "login"},
    priority = 1
)
public void testUserLogin() {
    // Test implementation
}
```

### Test Data Management

#### Using Data Providers

```java
@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
public void testWithData(String username, String password, String expectedResult) {
    // Test using provided data
}
```

#### Random Data Generation

```java
@Test
public void testWithRandomData() {
    String email = RandomDataGenerator.generateRandomEmail();
    String name = RandomDataGenerator.generateRandomFirstName();

    // Use generated data in test
}
```

## Page Object Model

### Creating Page Objects

Page objects should extend `BasePage` and encapsulate page-specific logic:

```java
package com.testautomation.pages;

import com.testautomation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Element locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.className("error-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page methods
    public void enterUsername(String username) {
        sendKeysToElement(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeysToElement(passwordField, password);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
```

### Using Page Objects in Tests

```java
@Test
public void testLoginFunctionality() {
    LoginPage loginPage = new LoginPage(driver);

    // Navigate to login page
    driver.get(ConfigReader.getBaseUrl() + "login");

    // Perform login
    loginPage.login("testuser", "password123");

    // Verify login success
    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
}
```

## Framework Features

### Exception Handling

The framework provides a hierarchical exception system for clear error categorization:

```java
try {
    // Test code that might fail
    WebElement element = driver.findElement(By.id("nonexistent"));
} catch (NoSuchElementException e) {
    throw new WebDriverException("Element Interaction",
        "Failed to find element with ID 'nonexistent'", e);
} catch (Exception e) {
    throw new FrameworkException("GENERAL_ERROR", "Test Execution",
        "Unexpected error during test execution", e);
}
```

### Test Listeners

Test listeners automatically track execution and provide detailed reporting:

```java
// Listeners are automatically configured in the framework
// They provide:
// - Test execution statistics
// - Automatic screenshot capture on failures
// - Detailed logging and error context
// - Performance metrics
```

### Retry Mechanism

Automatically retry flaky tests:

```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testThatMightBeFlaky() {
    // Test implementation
    // Will be retried up to 2 times if it fails
}
```

### Screenshot Capture

Automatic screenshots on test failures:

```java
@Test
public void testWithScreenshot() {
    try {
        // Test that might fail
        Assert.fail("Intentional failure for demonstration");
    } catch (Exception e) {
        // Screenshot is automatically captured
        throw e;
    }
}
```

## Best Practices

### Test Organization

1. **Group Related Tests**: Use TestNG groups to organize tests by feature
2. **Descriptive Names**: Use clear, descriptive test method names
3. **Single Responsibility**: Each test should verify one specific behavior
4. **Independent Tests**: Tests should not depend on each other

### Element Locators

1. **Prefer ID**: Use ID selectors when available
2. **CSS Selectors**: Use CSS selectors over XPath for better performance
3. **Page Objects**: Keep all locators in page object classes
4. **Meaningful Names**: Use descriptive names for locator variables

### Wait Strategies

1. **Explicit Waits**: Use explicit waits instead of `Thread.sleep()`
2. **Wait Utilities**: Leverage `WaitUtils` for common wait scenarios
3. **Page Load**: Wait for page load completion before interacting with elements

### Error Handling

1. **Custom Exceptions**: Use framework exception classes for clear error categorization
2. **Meaningful Messages**: Provide descriptive error messages
3. **Context Information**: Include relevant context in error messages
4. **Screenshot Capture**: Ensure screenshots are captured on failures

### Data Management

1. **Test Data**: Use data providers for data-driven testing
2. **Random Data**: Generate random data for comprehensive coverage
3. **Data Cleanup**: Clean up test data after test execution
4. **Environment Independence**: Make tests work across different environments

## Examples and Templates

### Basic Test Template

```java
@Test(description = "Verify basic functionality")
public void testBasicFunctionality() {
    // Arrange - Setup test data and conditions
    String expectedTitle = "Expected Page Title";

    // Act - Perform the action being tested
    driver.get(ConfigReader.getBaseUrl());

    // Assert - Verify the expected outcome
    String actualTitle = driver.getTitle();
    Assert.assertEquals(actualTitle, expectedTitle,
        "Page title should match expected");
}
```

### Page Object Template

```java
public class ExamplePage extends BasePage {

    // Element locators
    private By mainElement = By.id("main-element");
    private By actionButton = By.cssSelector(".action-button");

    public ExamplePage(WebDriver driver) {
        super(driver);
    }

    // Navigation methods
    public void navigateToPage() {
        driver.get(ConfigReader.getBaseUrl() + "example-page");
        waitForPageToLoad();
    }

    // Action methods
    public void performAction() {
        clickElement(actionButton);
    }

    // Verification methods
    public boolean isPageLoaded() {
        return isElementDisplayed(mainElement);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
```

### Data-Driven Test Template

```java
@Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
public void testDataDriven(String input, String expectedOutput) {
    // Arrange
    ExamplePage page = new ExamplePage(driver);
    page.navigateToPage();

    // Act
    page.performActionWithInput(input);

    // Assert
    String actualOutput = page.getOutput();
    Assert.assertEquals(actualOutput, expectedOutput,
        "Output should match expected for input: " + input);
}
```

### Test Suite Configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Example Test Suite" parallel="classes" thread-count="4">

    <test name="Smoke Tests">
        <groups>
            <run>
                <include name="smoke"/>
            </run>
        </groups>
        <classes>
            <class name="com.testautomation.tests.examples.ExampleTest"/>
        </classes>
    </test>

    <test name="Regression Tests">
        <groups>
            <run>
                <include name="regression"/>
            </run>
        </groups>
        <classes>
            <class name="com.testautomation.tests.examples.ExampleTest"/>
        </classes>
    </test>

</suite>
```

## Running Tests

### Basic Test Execution

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExampleTest

# Run specific test method
mvn test -Dtest=ExampleTest#testMethod

# Run with specific TestNG suite
mvn test -DsuiteXmlFile=src/test/resources/testng/testng.xml
```

### Test Groups

```bash
# Run smoke tests only
mvn test -Dgroups=smoke

# Run multiple groups
mvn test -Dgroups=smoke,regression

# Exclude specific groups
mvn test -DexcludedGroups=slow
```

### Parallel Execution

```bash
# Run tests in parallel
mvn test -Dparallel=true -DthreadCount=4

# Parallel execution is also configurable in testng.xml
```

## Reporting and Results

### TestNG Reports

Detailed reports are generated in `target/surefire-reports/`:

- Test execution summary
- Pass/fail statistics
- Execution timing
- Error details and stack traces

### Allure Reports

Beautiful, interactive reports with:

- Visual test execution statistics
- Screenshots on failures
- Step-by-step execution details
- Performance metrics and trends

### Screenshots

Automatic screenshots are captured:

- On test failures
- With descriptive names
- Automatically attached to Allure reports
- Stored in `screenshots/` directory

## Troubleshooting

### Common Issues

1. **Element Not Found**: Check locators, add explicit waits, verify page load
2. **Timing Issues**: Use explicit waits, check for dynamic content
3. **Browser Compatibility**: Update browser, check WebDriver compatibility
4. **Test Flakiness**: Add retry logic, use stable locators, implement proper waits

### Debug Mode

Enable debug logging for troubleshooting:

```bash
# Run with debug logging
mvn test -Dtest=ExampleTest -X

# Check detailed logs
tail -f target/surefire-reports/testng-results.xml
```

### Best Practices for Debugging

1. **Use Logging**: Leverage `LoggerUtil` for detailed execution tracking
2. **Take Screenshots**: Capture screenshots at key points for visual verification
3. **Check Configuration**: Verify configuration values are correct
4. **Review Reports**: Use Allure reports for detailed execution analysis

---

**Ready to contribute?** See [04-CONTRIBUTING.md](04-CONTRIBUTING.md) for contribution guidelines.

**Need help with setup?** See [02-SETUP.md](02-SETUP.md) for detailed setup instructions.
