# 📚 Template Guide - Test Automation Framework

This guide provides comprehensive patterns, best practices, and examples for using the Test Automation Framework effectively.

## 🏗️ Framework Architecture

### Package Structure

```
src/test/java/com/testautomation/
├── enums/           # Enums for browser types, severity levels, test types
├── exceptions/      # Custom exception classes for framework error handling
├── fixtures/        # Test data providers and fixtures
├── listeners/       # TestNG listeners for exception handling and reporting
├── pages/          # Page Object Model classes
├── tests/          # Test classes
│   ├── BaseTest.java           # Base test class with common setup
│   └── examples/               # Example test implementations
└── utilities/      # Utility classes for common operations
```

### Key Components

- **BaseTest**: Common setup/teardown for all tests
- **WebDriverManager**: Browser initialization and management
- **Page Objects**: Encapsulated page interactions
- **Utility Classes**: Reusable helper methods
- **Exception Handling**: Comprehensive error management system
- **Allure Integration**: Beautiful test reporting

## 🚨 Exception Handling System

The framework implements a robust exception handling system that provides clear error categorization, detailed logging, and recovery strategies. This system is essential for maintaining test stability and providing actionable debugging information.

### 🎯 Why Exception Handling is Important

1. **Test Stability**: Prevents tests from failing silently or with unclear error messages
2. **Debugging Efficiency**: Provides detailed context about what went wrong and where
3. **Error Recovery**: Identifies which errors can be retried and which require manual intervention
4. **Professional Quality**: Enterprise-grade error handling that scales with team size
5. **Maintenance**: Centralized error handling makes the framework easier to maintain and update

### 🏗️ Exception Hierarchy

The framework uses a hierarchical exception structure that provides increasing levels of detail:

```
FrameworkException (Base)
├── TestSetupException
├── WebDriverException
└── ConfigurationException
```

#### 1. **FrameworkException** - The Foundation

**What it is**: The base exception class that all framework exceptions inherit from.

**How it works**:

- Provides error codes for categorization
- Tracks which component caused the error
- Records timestamps for debugging
- Maintains the original cause exception

**Why it's useful**:

- **Consistent Error Format**: All errors follow the same structure
- **Error Categorization**: Error codes help identify problem types
- **Component Tracking**: Know exactly which part of the framework failed
- **Timing Information**: Understand when errors occurred in relation to other events

```java
// Example usage
throw new FrameworkException("WEBDRIVER_ERROR", "ChromeDriver",
    "Failed to initialize Chrome browser", originalException);
```

#### 2. **TestSetupException** - Test Lifecycle Errors

**What it is**: Specific exception for failures during test setup, execution, or teardown phases.

**How it works**:

- Catches errors during test initialization
- Handles WebDriver setup failures
- Manages test data generation issues
- Tracks navigation and page loading problems

**Why it's useful**:

- **Setup Validation**: Ensures tests start with proper configuration
- **Resource Management**: Tracks WebDriver and test data initialization
- **Navigation Handling**: Manages URL loading and page verification
- **Cleanup Assurance**: Ensures proper test environment setup

```java
// Example usage
throw new TestSetupException("WebDriver",
    "Failed to initialize Chrome browser", driverException);
```

#### 3. **WebDriverException** - Browser Interaction Errors

**What it is**: Handles all WebDriver-related failures including browser initialization, navigation, and element interaction.

**How it works**:

- Manages browser driver creation for different browsers
- Handles browser option configuration failures
- Tracks element interaction errors
- Manages browser cleanup and resource management

**Why it's useful**:

- **Browser Management**: Centralized handling of all browser-related errors
- **Cross-Browser Support**: Consistent error handling across Chrome, Firefox, Edge, Safari
- **Resource Cleanup**: Ensures browsers are properly closed even on failures
- **Option Configuration**: Handles browser-specific configuration errors

```java
// Example usage
throw new WebDriverException("ChromeDriver",
    "Failed to create Chrome options", optionsException);
```

#### 4. **ConfigurationException** - Framework Configuration Errors

**What it is**: Manages failures related to framework configuration, properties, and settings.

**How it works**:

- Loads configuration files from classpath
- Validates required configuration properties
- Handles configuration file loading errors
- Provides fallback values for optional settings

**Why it's useful**:

- **Configuration Validation**: Ensures all required settings are present
- **Environment Independence**: Works across different deployment environments
- **Property Management**: Centralized handling of all configuration values
- **Error Recovery**: Provides sensible defaults when configuration fails

```java
// Example usage
throw new ConfigurationException("ConfigReader",
    "Configuration file not found", fileNotFoundException);
```

### 🔧 How Exception Handling Works

#### 1. **Error Detection and Categorization**

```java
try {
    // Framework operation
    webDriverManager.initiateDriver(browserName);
} catch (Exception e) {
    // Categorize and handle the error
    if (e instanceof WebDriverException) {
        ExceptionHandler.handleWebDriverException((WebDriverException) e);
    } else {
        ExceptionHandler.handleGenericException(e, "WebDriver Initialization");
    }
}
```

#### 2. **Error Context and Logging**

Each exception captures detailed context:

- **Error Code**: Unique identifier for the error type
- **Component**: Which part of the framework failed
- **Message**: Human-readable description of the problem
- **Timestamp**: When the error occurred
- **Cause**: The underlying exception that triggered the error

#### 3. **Recovery Assessment**

The system automatically determines if an error is recoverable:

- **Recoverable**: Configuration errors, WebDriver timeouts (can retry)
- **Non-Recoverable**: Test setup failures, invalid configurations (require manual intervention)

#### 4. **User-Friendly Reporting**

Errors are transformed into actionable information:

- Clear error descriptions
- Suggested solutions
- Component identification
- Recovery recommendations

### 📊 Exception Handling in Action

#### **Before (Basic Exception Handling)**

```java
try {
    driver.get(url);
} catch (Exception e) {
    // Generic error - hard to debug
    throw new RuntimeException("Navigation failed", e);
}
```

#### **After (Framework Exception Handling)**

```java
try {
    driver.get(url);
} catch (Exception e) {
    // Detailed error with context
    throw new WebDriverException("Navigation",
        "Failed to navigate to URL: " + url, e);
}
```

**Result**: Instead of "Navigation failed", you get:

- **Error Code**: `WEBDRIVER_ERROR`
- **Component**: `Navigation`
- **Message**: `Failed to navigate to URL: https://example.com`
- **Cause**: Original exception with full stack trace
- **Recoverable**: Yes (can retry)

### 🎯 Best Practices for Using Exception Handling

#### 1. **Always Use Specific Exceptions**

```java
// ❌ Don't do this
throw new RuntimeException("Something went wrong");

// ✅ Do this instead
throw new WebDriverException("ElementInteraction",
    "Failed to click element with locator: " + locator);
```

#### 2. **Provide Meaningful Context**

```java
// ❌ Generic message
throw new TestSetupException("Setup failed");

// ✅ Specific context
throw new TestSetupException("DataGeneration",
    "Failed to generate test data for user registration test");
```

#### 3. **Handle Exceptions Appropriately**

```java
try {
    // Your test logic
} catch (WebDriverException e) {
    // WebDriver errors might be recoverable
    if (ExceptionHandler.isRecoverable(e)) {
        LoggerUtil.warning("Recoverable error, retrying...");
        // Implement retry logic
    } else {
        // Non-recoverable, fail the test
        throw e;
    }
}
```

#### 4. **Use the Exception Handler**

```java
// Centralized exception handling
try {
    // Framework operation
} catch (FrameworkException e) {
    ExceptionHandler.handleFrameworkException(e);
}
```

### 🚀 Benefits of the Exception Handling System

#### **For Developers**

- **Faster Debugging**: Clear error context and component identification
- **Better Error Messages**: Actionable information instead of generic failures
- **Consistent Handling**: Same error handling patterns across the framework
- **Recovery Options**: Automatic identification of retry opportunities

#### **For Test Engineers**

- **Reduced Test Flakiness**: Better error recovery and retry mechanisms
- **Clearer Test Reports**: Detailed error information in test results
- **Faster Issue Resolution**: Know exactly what failed and why
- **Professional Quality**: Enterprise-grade error handling

#### **For DevOps/CI Teams**

- **Better Monitoring**: Detailed error tracking and categorization
- **Faster Deployments**: Clear error identification reduces debugging time
- **Improved Reliability**: Better error handling increases test stability
- **Scalability**: Exception handling scales with team and test growth

### 🔍 Debugging with Exception Handling

#### **Error Analysis Workflow**

1. **Identify Error Type**: Check the exception class and error code
2. **Locate Component**: Find which part of the framework failed
3. **Review Context**: Understand the specific operation that failed
4. **Check Recoverability**: Determine if retry is possible
5. **Implement Solution**: Fix the root cause or add retry logic

#### **Common Error Scenarios and Solutions**

| Error Type                | Component    | Common Cause          | Solution                                                   |
| ------------------------- | ------------ | --------------------- | ---------------------------------------------------------- |
| `CONFIG_FILE_NOT_FOUND`   | ConfigReader | Missing config file   | Ensure `config.properties` exists in `src/test/resources/` |
| `WEBDRIVER_ERROR`         | ChromeDriver | Driver binary missing | Update Chrome or use Selenium Manager                      |
| `TEST_SETUP_ERROR`        | Navigation   | Network timeout       | Increase timeout or add retry logic                        |
| `CONFIG_VALIDATION_ERROR` | ConfigReader | Invalid properties    | Check property values in config file                       |

The exception handling system transforms your framework from a basic test runner into a **professional, enterprise-grade automation solution** that provides clear error information, enables quick debugging, and maintains high test stability. 🎯

## 🎧 Test Listeners System

The framework implements a comprehensive test listeners system that provides real-time test execution monitoring, detailed reporting, and centralized test management. This system is essential for understanding test execution flow, debugging issues, and maintaining comprehensive test execution records.

### 🎯 Why Test Listeners are Important

1. **Real-Time Monitoring**: Track test execution as it happens, not just after completion
2. **Comprehensive Reporting**: Capture detailed information about test start, success, failure, and skip events
3. **Centralized Management**: Handle all test events from a single location
4. **Debugging Support**: Provide context about test execution environment and timing
5. **Performance Tracking**: Monitor test execution times and identify bottlenecks
6. **Error Context**: Capture detailed information when tests fail or encounter issues

### 🏗️ Test Listeners Architecture

The framework uses two main listener types that work together to provide comprehensive test monitoring:

```
TestListener (Primary)
├── Test Execution Tracking
├── Success/Failure Counting
├── Browser and Thread Information
└── Comprehensive Logging

ExceptionTestListener (Specialized)
├── Exception Categorization
├── Error Context Capture
├── Recovery Assessment
└── Centralized Error Handling
```

#### 1. **TestListener** - Comprehensive Test Execution Tracking

**What it is**: The primary listener that monitors all test execution events and provides detailed tracking and reporting.

**How it works**:

- **Test Lifecycle Monitoring**: Tracks test start, success, failure, and skip events
- **Execution Statistics**: Maintains counters for total tests, passed tests, failed tests, and skipped tests
- **Thread-Safe Counting**: Uses thread-safe counters for parallel execution support
- **Browser Information**: Captures and logs browser details for each test
- **Comprehensive Logging**: Provides detailed information about each test event

**Why it's useful**:

- **Execution Visibility**: See exactly what's happening during test runs
- **Performance Monitoring**: Track how long tests take to execute
- **Parallel Execution Support**: Handle multiple tests running simultaneously
- **Debugging Context**: Understand test execution environment and timing

```java
// Example usage in test class
@Listeners({TestListener.class})
public class ExampleTest extends BaseTest {
    // All test executions are automatically tracked
    @Test
    public void testMethod() {
        // Test implementation
    }
}
```

**Key Features**:

- **Real-Time Tracking**: Monitor test execution as it happens
- **Thread Safety**: Support for parallel test execution
- **Detailed Logging**: Comprehensive information about each test event
- **Statistics Collection**: Track execution counts and timing
- **Browser Context**: Capture browser information for debugging

#### 2. **ExceptionTestListener** - Specialized Exception Handling

**What it is**: A specialized listener that focuses specifically on exception handling and error context capture during test execution.

**How it works**:

- **Exception Categorization**: Automatically identifies different types of exceptions
- **Error Context Capture**: Records detailed information about the test environment when errors occur
- **Recovery Assessment**: Determines if errors are recoverable or require manual intervention
- **Centralized Error Handling**: Provides consistent error handling across all tests
- **Detailed Error Logging**: Captures comprehensive error information for debugging

**Why it's useful**:

- **Error Understanding**: Quickly identify what type of error occurred
- **Context Preservation**: Maintain error context for effective debugging
- **Recovery Planning**: Know which errors can be retried automatically
- **Consistent Handling**: Same error handling approach across all tests

```java
// Automatically applied to all tests
// No need to add @Listeners annotation
public class ExampleTest extends BaseTest {
    // Exception handling is automatically managed
    @Test
    public void testMethod() {
        // If an exception occurs, it's automatically categorized and logged
    }
}
```

**Key Features**:

- **Automatic Exception Categorization**: Framework, TestSetup, WebDriver, Configuration
- **Error Context Preservation**: Browser, URL, page title, thread information
- **Recovery Assessment**: Identify retry opportunities automatically
- **Comprehensive Error Logging**: Detailed error information and stack traces
- **Centralized Management**: Consistent error handling across the framework

### 🔧 How Test Listeners Work

#### 1. **Test Execution Flow**

```java
// Test execution follows this flow:
@Test
public void testMethod() {
    // 1. TestListener.onTestStart() - Test begins
    // 2. Test execution
    // 3. TestListener.onTestSuccess() - Test passes
    //    OR
    // 4. TestListener.onTestFailure() - Test fails
    //    OR
    // 5. TestListener.onTestSkipped() - Test is skipped
    // 6. TestListener.onTestFinish() - Test completes
}
```

#### 2. **Exception Handling Flow**

```java
// When an exception occurs:
try {
    // Test execution
} catch (Exception e) {
    // 1. ExceptionTestListener.onTestFailure() is triggered
    // 2. Exception is automatically categorized
    // 3. Error context is captured
    // 4. Detailed error information is logged
    // 5. Recovery assessment is performed
}
```

#### 3. **Parallel Execution Support**

```java
// Multiple tests can run simultaneously:
@Test(threadPoolSize = 3)
public void parallelTest1() {
    // TestListener tracks this test independently
}

@Test(threadPoolSize = 3)
public void parallelTest2() {
    // TestListener tracks this test independently
}
```

### 📊 Test Listeners in Action

#### **Test Start Event**

```
[INFO] === Test Started ===
[INFO] Test Name: testHomepageLoads
[INFO] Test Class: ExampleTest
[INFO] Test Method: testHomepageLoads
[INFO] Test Thread: TestNG-test=Example Tests-1
[INFO] Browser: Chrome 120.0.6099.109
[INFO] Start Time: 2024-01-15 10:30:15
[INFO] ====================
```

#### **Test Success Event**

```
[INFO] === Test Success ===
[INFO] Test Name: testHomepageLoads
[INFO] Test Class: ExampleTest
[INFO] Test Method: testHomepageLoads
[INFO] Test Thread: TestNG-test=Example Tests-1
[INFO] Browser: Chrome 120.0.6099.109
[INFO] End Time: 2024-01-15 10:30:18
[INFO] Duration: 3.2 seconds
[INFO] Status: PASSED
[INFO] ====================
```

#### **Test Failure Event**

```
[ERROR] === Test Failure ===
[ERROR] Test Name: testHomepageLoads
[ERROR] Test Class: ExampleTest
[ERROR] Test Method: testHomepageLoads
[ERROR] Test Thread: TestNG-test=Example Tests-1
[ERROR] Browser: Chrome 120.0.6099.109
[ERROR] Exception Type: WebDriverException
[ERROR] Exception Message: Element not found: By.id("non-existent-element")
[ERROR] Current URL: https://demoqa.com/
[ERROR] Page Title: ToolsQA
[ERROR] Error Context: Element interaction failed
[ERROR] ====================
```

#### **Suite Completion Summary**

```
[INFO] === Test Suite Summary ===
[INFO] Total Tests Started: 15
[INFO] Total Tests Finished: 15
[INFO] Total Tests Passed: 12
[INFO] Total Tests Failed: 2
[INFO] Total Tests Skipped: 1
[INFO] Success Rate: 80.0%
[INFO] Execution Time: 45.3 seconds
[INFO] =========================
```

### 🎯 Benefits of the Test Listeners System

#### **For Developers**

- **Real-Time Visibility**: See test execution progress as it happens
- **Quick Debugging**: Immediate access to test context and error information
- **Performance Insights**: Understand which tests take longest to execute
- **Parallel Execution Support**: Handle multiple tests running simultaneously

#### **For Test Engineers**

- **Comprehensive Monitoring**: Track all aspects of test execution
- **Error Context**: Understand exactly what happened when tests fail
- **Execution Statistics**: Know test success rates and execution patterns
- **Debugging Efficiency**: Faster issue resolution with detailed context

#### **For DevOps/CI Teams**

- **Build Monitoring**: Real-time visibility into test execution progress
- **Failure Analysis**: Quick identification of test failures and their causes
- **Performance Tracking**: Monitor test execution times and identify bottlenecks
- **Reporting Integration**: Rich information for CI/CD dashboards and notifications

### 🔍 Using Test Listeners Effectively

#### 1. **Basic Listener Integration**

```java
// Add listeners to your test class
@Listeners({TestListener.class})
public class MyTest extends BaseTest {

    @Test
    public void testMethod() {
        // Test implementation
        // All events are automatically tracked
    }
}
```

#### 2. **Custom Listener Configuration**

```java
// You can combine multiple listeners
@Listeners({TestListener.class, CustomListener.class})
public class MyTest extends BaseTest {
    // Multiple listeners work together
}
```

#### 3. **Listener in TestNG XML**

```xml
<!-- Configure listeners globally in testng.xml -->
<suite name="Test Suite">
    <listeners>
        <listener class-name="com.testautomation.listeners.TestListener"/>
        <listener class-name="com.testautomation.listeners.ExceptionTestListener"/>
    </listeners>

    <test name="Test Suite">
        <classes>
            <class name="com.testautomation.tests.examples.ExampleTest"/>
        </classes>
    </test>
</suite>
```

#### 4. **Listener in Maven Configuration**

```xml
<!-- Configure listeners in pom.xml -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0</version>
    <configuration>
        <properties>
            <property>
                <name>listener</name>
                <value>com.testautomation.listeners.TestListener,com.testautomation.listeners.ExceptionTestListener</value>
            </property>
        </properties>
    </configuration>
</plugin>
```

### 🚀 Advanced Listener Features

#### 1. **Thread-Safe Execution Tracking**

```java
// Listeners automatically handle parallel execution
@Test(threadPoolSize = 5)
public void parallelTest() {
    // Each thread execution is tracked independently
    // No conflicts between parallel test executions
}
```

#### 2. **Browser Information Capture**

```java
// Listeners automatically capture browser details
@Test
public void testWithBrowserInfo() {
    // Browser information is automatically logged
    // Chrome version, capabilities, etc.
}
```

#### 3. **Exception Categorization**

```java
// Listeners automatically categorize exceptions
@Test
public void testWithExceptionHandling() {
    try {
        // Test logic
    } catch (WebDriverException e) {
        // Automatically categorized as WebDriver error
        // Detailed context is captured
    }
}
```

#### 4. **Performance Monitoring**

```java
// Listeners track execution timing
@Test
public void performanceTest() {
    // Start time is automatically recorded
    // End time is automatically recorded
    // Duration is calculated and logged
}
```

### 🔧 Customizing Test Listeners

#### 1. **Extending TestListener**

```java
public class CustomTestListener extends TestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        // Custom success handling
        super.onTestSuccess(result);

        // Additional custom logic
        LoggerUtil.info("Custom success handling for: " + result.getName());
    }
}
```

#### 2. **Extending ExceptionTestListener**

```java
public class CustomExceptionListener extends ExceptionTestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Custom failure handling
        super.onTestFailure(result);

        // Additional custom logic
        sendFailureNotification(result);
    }
}
```

### 📊 Listener Configuration Options

#### 1. **Global Configuration**

```xml
<!-- Apply to all tests in the suite -->
<suite name="Test Suite">
    <listeners>
        <listener class-name="com.testautomation.listeners.TestListener"/>
    </listeners>
</suite>
```

#### 2. **Test-Specific Configuration**

```java
// Apply only to specific test classes
@Listeners({TestListener.class})
public class SpecificTest extends BaseTest {
    // Only this class uses the listener
}
```

#### 3. **Method-Level Configuration**

```java
// Apply to specific test methods
@Test
@Listeners({TestListener.class})
public void specificTestMethod() {
    // Only this method uses the listener
}
```

### 🎯 Best Practices for Using Test Listeners

#### 1. **Choose the Right Listener**

```java
// For general test tracking
@Listeners({TestListener.class})

// For exception handling (automatically applied)
// No annotation needed - ExceptionTestListener is global
```

#### 2. **Combine Listeners Effectively**

```java
// Multiple listeners work together
@Listeners({TestListener.class, CustomListener.class})
public class MyTest extends BaseTest {
    // Both listeners will be active
}
```

#### 3. **Avoid Listener Conflicts**

```java
// Don't duplicate listener functionality
// TestListener already provides comprehensive tracking
// ExceptionTestListener already handles exceptions
```

#### 4. **Use Listeners Consistently**

```java
// Apply the same listener pattern across your test suite
// Consistency makes debugging easier
// Standardized reporting across all tests
```

### 🚨 Troubleshooting Test Listeners

#### Common Issues and Solutions

1. **Listener Not Working**

   - Check if listener class is in the correct package
   - Verify listener class extends correct TestNG interfaces
   - Ensure listener is properly configured in TestNG XML or annotations

2. **Duplicate Event Handling**

   - Avoid multiple listeners with overlapping functionality
   - Use `super` calls when extending listeners
   - Check for conflicting listener configurations

3. **Performance Impact**

   - Keep listener logic lightweight
   - Avoid heavy operations in listener methods
   - Use asynchronous logging for heavy operations

4. **Parallel Execution Issues**

   - Ensure listeners are thread-safe
   - Use thread-local variables when needed
   - Test listeners with parallel execution

### 🌟 Test Listeners vs. Traditional Logging

#### **Traditional Approach**

```java
@Test
public void traditionalTest() {
    LoggerUtil.info("Test started");

    try {
        // Test logic
        LoggerUtil.info("Test passed");
    } catch (Exception e) {
        LoggerUtil.error("Test failed: " + e.getMessage());
        throw e;
    }
}
```

#### **Listener-Enhanced Approach**

```java
@Test
public void listenerEnhancedTest() {
    // No manual logging needed
    // Test execution is automatically tracked
    // All events are automatically logged
    // Exception handling is automatic
}
```

**Result**: Cleaner test code with comprehensive automatic tracking and reporting.

The test listeners system transforms your framework from basic test execution into a **comprehensive, professional monitoring solution** that provides real-time visibility, detailed reporting, and centralized management of all test activities. 🎯

## 📝 Writing Tests

### 1. Basic Test Structure

```java
public class MyTest extends BaseTest {

    @Test(description = "Test description")
    @Epic("Epic Name")
    @Feature("Feature Name")
    @Story("Story Name")
    @Severity(SeverityLevel.CRITICAL)
    public void testMethodName() {
        // Test implementation
        driver.get("https://example.com");

        // Assertions
        Assert.assertEquals(driver.getTitle(), "Expected Title");
    }
}
```

### 2. Test Annotations

#### Allure Annotations

```java
@Epic("User Management")           // High-level feature grouping
@Feature("User Registration")       // Feature grouping
@Story("Email Validation")         // User story grouping
@Severity(SeverityLevel.CRITICAL)  // Test importance
@Description("Detailed description") // Test description
```

#### TestNG Annotations

```java
@Test(description = "Test description")
@Test(groups = {"smoke", "regression"})
@Test(dependsOnMethods = "setupMethod")
@Test(dataProvider = "testData")
@BeforeMethod
@AfterMethod
@Parameters("browser")
```

### 3. Page Object Model

#### Creating a Page Class

```java
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        sendKeysToElement(usernameField, username);
        sendKeysToElement(passwordField, password);
        clickElement(loginButton);
    }

    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(usernameField);
    }
}
```

#### Using Page Objects in Tests

```java
@Test
public void testLogin() {
    LoginPage loginPage = new LoginPage(driver);

    Assert.assertTrue(loginPage.isLoginFormDisplayed());
    loginPage.login("testuser", "password");

    // Verify login success
    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
}
```

## 🔧 Utility Usage

### 1. Wait Utilities

```java
// Wait for element to be present
WebElement element = WaitUtils.waitForElementPresent(driver, By.id("element"), 10);

// Wait for element to be visible
WebElement visibleElement = WaitUtils.waitForElementVisible(driver, By.className("visible"), 10);

// Wait for element to be clickable
WebElement clickableElement = WaitUtils.waitForElementClickable(driver, By.xpath("//button"), 10);

// Wait for title to contain text
boolean titleContains = WaitUtils.waitForTitleContains(driver, "Expected Title", 10);

// Wait for URL to contain text
boolean urlContains = WaitUtils.waitForUrlContains(driver, "expected-path", 10);
```

### 2. Screenshot Utilities

```java
// Take screenshot with custom name
String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "test-screenshot");

// Take screenshot as bytes (for Allure attachment)
byte[] screenshotBytes = ScreenshotUtils.takeScreenshotAsBytes(driver);

// Clean up old screenshots (older than 7 days)
ScreenshotUtils.cleanupOldScreenshots(7);
```

### 3. Random Data Generation

```java
// Generate random test data
String randomEmail = RandomDataGenerator.getRandomEmail();
String randomName = RandomDataGenerator.getRandomFirstName();
String randomPhone = RandomDataGenerator.getRandomPhoneNumber();
String randomAddress = RandomDataGenerator.getRandomAddress();
```

### 4. Configuration Management

```java
// Get configuration values
String baseUrl = ConfigReader.getBaseUrl();
String defaultBrowser = ConfigReader.getDefaultBrowser();
boolean isHeadless = ConfigReader.isHeadless();
int timeout = ConfigReader.getExplicitWait();
```

## 📊 Data-Driven Testing

### 1. Using DataProvider

```java
@DataProvider(name = "loginData")
public static Object[][] getLoginData() {
    return new Object[][] {
        {"user1@example.com", "password1"},
        {"user2@example.com", "password2"},
        {"user3@example.com", "password3"}
    };
}

@Test(dataProvider = "loginData")
public void testLoginWithData(String email, String password) {
    // Test implementation using provided data
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(email, password);

    // Verify login
    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
}
```

### 2. External Data Sources

```java
@DataProvider(name = "excelData")
public static Object[][] getExcelData() throws IOException {
    // Read from Excel file
    // Implementation depends on your Excel library
    return new Object[][] {
        {"data1", "data2"},
        {"data3", "data4"}
    };
}
```

## 🚀 Parallel Execution

### 1. TestNG XML Configuration

```xml
<suite name="Parallel Test Suite" parallel="classes" thread-count="4">
    <test name="Test Suite 1" parallel="methods" thread-count="2">
        <classes>
            <class name="com.testautomation.tests.examples.ExampleTest"/>
        </classes>
    </test>
</suite>
```

### 2. Maven Configuration

```bash
# Run tests in parallel
mvn test -Dparallel=methods -DthreadCount=4

# Run specific test groups in parallel
mvn test -Dgroups=smoke -Dparallel=classes -DthreadCount=3
```

## 🎨 Allure Reporting

### 1. Test Descriptions

```java
@Test(description = "Verify user can login with valid credentials")
@Epic("User Management")
@Feature("Authentication")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
public void testValidLogin() {
    // Test implementation
}
```

### 2. Attachments

```java
@Attachment(value = "Page Screenshot", type = "image/png")
public byte[] attachScreenshot() {
    return ScreenshotUtils.takeScreenshotAsBytes(driver);
}

@Attachment(value = "Test Data", type = "text/plain")
public String attachTestData() {
    return "Username: " + username + ", Password: " + password;
}
```

### 3. Steps

```java
@Test
public void testWithSteps() {
    Allure.step("Navigate to login page", () -> {
        driver.get("https://example.com/login");
    });

    Allure.step("Enter credentials", () -> {
        // Enter username and password
    });

    Allure.step("Click login button", () -> {
        // Click login
    });

    Allure.step("Verify successful login", () -> {
        // Verify login success
    });
}
```

## 🧪 Test Groups and Categories

### 1. Test Grouping

```java
@Test(groups = {"smoke", "regression"})
public void testCriticalFunctionality() {
    // Critical test that runs in both smoke and regression
}

@Test(groups = {"smoke"})
public void testBasicFunctionality() {
    // Basic test that runs only in smoke
}

@Test(groups = {"regression"})
public void testDetailedFunctionality() {
    // Detailed test that runs only in regression
}
```

### 2. Test Categories

```java
@Test(groups = {"ui", "positive"})
public void testUIElements() {
    // UI test with positive scenarios
}

@Test(groups = {"api", "negative"})
public void testAPIErrorHandling() {
    // API test with negative scenarios
}
```

## 🔍 Best Practices

### 1. Test Design

- **One assertion per test** - Keep tests focused and easy to debug
- **Descriptive test names** - Use clear, descriptive method names
- **Independent tests** - Tests should not depend on each other
- **Clean setup/teardown** - Always clean up test data

### 2. Page Objects

- **Single responsibility** - Each page class handles one page
- **Encapsulation** - Hide WebDriver details from test classes
- **Reusability** - Make methods reusable across tests
- **Maintainability** - Centralize locator changes

### 3. Test Data

- **External data** - Use external files for test data
- **Random data** - Use random data for unique test runs
- **Data providers** - Leverage TestNG data providers
- **Clean data** - Always start with clean test data

### 4. Error Handling

- **Graceful failures** - Handle expected errors gracefully
- **Meaningful messages** - Provide clear error messages
- **Screenshots** - Capture screenshots on failures
- **Logging** - Use comprehensive logging

## 📁 File Organization

### Recommended Structure

```
src/test/java/com/testautomation/
├── tests/
│   ├── ui/              # UI tests
│   ├── api/             # API tests
│   ├── integration/     # Integration tests
│   └── performance/     # Performance tests
├── pages/
│   ├── common/          # Common page elements
│   ├── forms/           # Form-related pages
│   └── navigation/      # Navigation pages
└── utilities/
    ├── api/             # API utilities
    ├── database/        # Database utilities
    └── reporting/       # Reporting utilities
```

## 🎯 Common Patterns

### 1. Test Setup Pattern

```java
@BeforeMethod
public void setUp() {
    // Navigate to test page
    driver.get(ConfigReader.getBaseUrl() + "test-page");

    // Wait for page to load
    WaitUtils.sleep(2000);

    // Verify page loaded
    Assert.assertTrue(driver.getTitle().contains("Expected Title"));
}
```

### 2. Test Cleanup Pattern

```java
@AfterMethod
public void tearDown() {
    // Clean up test data
    // Reset application state
    // Take screenshot on failure
    if (result.getStatus() == ITestResult.FAILURE) {
        ScreenshotUtils.takeScreenshot(driver, "test-failure");
    }
}
```

### 3. Assertion Pattern

```java
@Test
public void testWithAssertions() {
    // Arrange
    String expectedTitle = "Expected Page Title";

    // Act
    driver.get("https://example.com");

    // Assert
    String actualTitle = driver.getTitle();
    Assert.assertEquals(actualTitle, expectedTitle, "Page title should match expected");
}
```

## 🚨 Troubleshooting

### Common Issues and Solutions

1. **Element Not Found**

   - Check if element is in DOM
   - Verify locator strategy
   - Add explicit waits
   - Check for iframes

2. **Timing Issues**

   - Use explicit waits instead of Thread.sleep()
   - Check for dynamic content loading
   - Verify page load completion

3. **Browser Compatibility**

   - Update browser to latest version
   - Check WebDriver compatibility
   - Use Selenium Manager for auto-driver management

4. **Test Flakiness**
   - Add retry logic for flaky tests
   - Use stable locators
   - Implement proper waits
   - Clean test environment

## 📚 Additional Resources

- [Selenium Documentation](https://selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Allure Documentation](https://docs.qameta.io/allure/)
- [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

**Happy Testing! 🎯**

This template guide provides a solid foundation for building robust, maintainable test automation solutions.
