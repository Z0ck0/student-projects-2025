# TestNG @Test Annotation Reference

The `@Test` annotation in TestNG marks a method or a class as part of the test suite. When applied to a class, all public methods are treated as tests; when applied to a method, it registers that single method as a test. The annotation supports various attributes that alter how the test is executed, such as setting dependencies, controlling invocation counts, or defining a timeout.

## Basic Usage

At a minimum, you declare a public method inside a test class and annotate it with `@Test`. TestNG will automatically invoke this method when the test suite runs.

```java
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void verifyLogin() {
        // Arrange test data and environment
        // Act: perform login action
        // Assert: verify successful login
    }
}
```

When multiple tests need to share setup or teardown logic, you can use TestNG's other lifecycle annotations such as `@BeforeMethod`, `@AfterMethod`, `@BeforeClass` and `@AfterClass`. However, this guide focuses on the attributes available on the `@Test` annotation itself.

## Summary of @Test Attributes

The table below summarizes each supported attribute and its purpose. Use these attributes inside the parentheses of your `@Test` annotation to customize test behavior. Attributes not listed in this table are not part of the `@Test` annotation but apply to configuration methods (e.g., `@BeforeMethod`).

| Attribute            | Purpose (short description)                                                                       |
| -------------------- | ------------------------------------------------------------------------------------------------- |
| `alwaysRun`          | Forces the test to run even if methods it depends on have failed                                  |
| `dataProvider`       | Specifies the name of a `@DataProvider` method that supplies test data                            |
| `dataProviderClass`  | Points to the class where the named data provider method resides                                  |
| `dependsOnGroups`    | Lists groups that must run successfully before this test                                          |
| `dependsOnMethods`   | Lists methods that must run successfully before this test                                         |
| `description`        | Provides a human‑readable description for the test                                                |
| `enabled`            | Enables or disables execution of the test; defaults to true                                       |
| `expectedExceptions` | Specifies a list of exception classes that the test is expected to throw                          |
| `groups`             | Assigns the test to one or more logical groups                                                    |
| `invocationCount`    | Runs the test method multiple times in a loop                                                     |
| `invocationTimeOut`  | Sets a time limit for the combined runtime of all invocations                                     |
| `priority`           | Determines the order in which tests are scheduled; lower values run first                         |
| `successPercentage`  | Defines the minimum percentage of successful invocations required to mark the test as passed      |
| `singleThreaded`     | Ensures all methods in the class run in the same thread; usable only on classes                   |
| `timeOut`            | Sets a maximum execution time (in milliseconds) for the test                                      |
| `threadPoolSize`     | Sets the size of the thread pool when using invocationCount; ignored if invocationCount is absent |

## Where and How to Use Each Attribute

### alwaysRun

This attribute applies when your test depends on other tests. By default, TestNG will not run a dependent test if its dependencies fail. Setting `alwaysRun = true` forces the test to run anyway.

```java
@Test(dependsOnMethods = {"createUser"}, alwaysRun = true)
public void verifyUserCleanup() {
    // This test will run even if createUser() fails or is skipped.
}
```

### dataProvider and dataProviderClass

For data‑driven tests, use `@DataProvider` to supply parameters. The `dataProvider` attribute names the provider, while `dataProviderClass` specifies the class where the provider is defined if it lives outside the test class.

```java
// Data provider defined in the same class
@DataProvider(name = "loginData")
public Object[][] loginData() {
    return new Object[][]{{"alice", "password"}, {"bob", "secret"}};
}

@Test(dataProvider = "loginData")
public void verifyLogin(String username, String password) {
    // Use the parameters in your test logic
}

// Data provider defined in another class
public class TestDataProviders {
    @DataProvider(name = "searchTerms")
    public static Object[][] searchTerms() {
        return new Object[][]{{"testng"}, {"selenium"}};
    }
}

@Test(dataProvider = "searchTerms", dataProviderClass = TestDataProviders.class)
public void verifySearch(String term) {
    // Parameterised test using data from TestDataProviders
}
```

### dependsOnGroups and dependsOnMethods

Use these attributes to express dependencies between tests or groups. TestNG will skip this test if any dependency fails.

```java
@Test(groups = {"smoke"})
public void createUser() {
    // Implementation
}

@Test(dependsOnMethods = {"createUser"})
public void deleteUser() {
    // Runs only if createUser() passes
}

@Test(groups = {"regression"}, dependsOnGroups = {"smoke"})
public void updateUser() {
    // Runs only after all smoke tests have completed successfully
}
```

### description and enabled

The `description` attribute documents the test's purpose, which appears in reports. Setting `enabled = false` temporarily disables a test without removing its code.

```java
@Test(description = "Verify that a user can reset their password", enabled = true)
public void verifyPasswordReset() {
    // Test logic
}

@Test(enabled = false)
public void obsoleteTest() {
    // This test will be skipped
}
```

### expectedExceptions

Specify one or more exception classes that the test must throw. If the test does not throw any of the listed exceptions, it will fail.

```java
@Test(expectedExceptions = {IllegalArgumentException.class})
public void verifyInvalidInputThrowsException() {
    // The following call must throw IllegalArgumentException
    myService.process(null);
}
```

### groups

Grouping lets you logically organize tests and selectively run subsets. You can assign tests to multiple groups by listing them in the `groups` attribute.

```java
@Test(groups = {"regression", "ui"})
public void verifyNavigationMenu() {
    // UI test that also belongs to the regression suite
}
```

### invocationCount, invocationTimeOut, and threadPoolSize

Use these attributes to run a test multiple times, optionally in parallel. `invocationCount` sets how many times the test runs. `threadPoolSize` determines the number of concurrent threads when `invocationCount > 1`. `invocationTimeOut` places a limit on the total duration of all invocations.

```java
@Test(invocationCount = 5, threadPoolSize = 2, invocationTimeOut = 10000)
public void verifyRepeatedLogin() {
    // This test will run 5 times across 2 threads and must complete within 10 seconds total.
}
```

### priority

TestNG schedules tests in ascending order of priority. Lower numbers run before higher numbers. If no priority is set, the default value is zero.

```java
@Test(priority = 1)
public void firstTest() {
    // Runs before the second test
}

@Test(priority = 2)
public void secondTest() {
    // Runs after firstTest()
}
```

### successPercentage

When a test is invoked multiple times, you can define the acceptable percentage of passes. The test will fail if fewer than `successPercentage` percent of runs succeed.

```java
@Test(invocationCount = 10, successPercentage = 90)
public void flakyServiceTest() {
    // Test logic that may intermittently fail
}
```

### singleThreaded

Apply `singleThreaded = true` at the class level to ensure all tests in the class run on the same thread. This setting is ignored on individual methods and is useful when tests share a non‑thread‑safe resource.

```java
@Test(singleThreaded = true)
public class UserManagementTests {
    @Test
    public void testAddUser() { /* ... */ }
    @Test
    public void testDeleteUser() { /* ... */ }
}
```

### timeOut

Sets a hard limit on how long a single test invocation may run. If the method execution exceeds the specified milliseconds, TestNG marks the test as failed.

```java
@Test(timeOut = 5000)
public void verifySlowOperation() {
    // This test will fail if it runs longer than 5 seconds
}
```

## Practical Examples

### Complete Test Method with Multiple Attributes

```java
@Test(
    description = "Verify user login with valid credentials",
    groups = {"smoke", "login"},
    priority = 1,
    timeOut = 10000,
    enabled = true
)
public void testValidUserLogin() {
    // Test implementation
}
```

### Test with Dependencies and Groups

```java
@Test(groups = {"setup"})
public void createTestUser() {
    // Create test user
}

@Test(groups = {"login"})
public void testUserLogin() {
    // Test login
}

@Test(
    groups = {"cleanup"},
    dependsOnGroups = {"login"},
    alwaysRun = true
)
public void cleanupTestUser() {
    // Clean up test user
}
```

### Data-Driven Test

```java
@DataProvider(name = "userCredentials")
public Object[][] getUserCredentials() {
    return new Object[][] {
        {"admin", "admin123", true},
        {"user", "password", true},
        {"invalid", "wrong", false}
    };
}

@Test(
    dataProvider = "userCredentials",
    description = "Test login with various user credentials"
)
public void testLoginWithCredentials(String username, String password, boolean expectedResult) {
    // Test implementation using parameters
}
```

## Best Practices

1. **Use descriptive descriptions**: Always provide clear, meaningful descriptions for your tests
2. **Group logically**: Organize tests into logical groups for better test management
3. **Set appropriate priorities**: Use priority attributes to control test execution order when needed
4. **Handle timeouts**: Set reasonable timeouts to prevent tests from hanging indefinitely
5. **Use dependencies wisely**: Avoid complex dependency chains that can make tests brittle
6. **Enable/disable strategically**: Use the enabled attribute to temporarily disable tests without removing code

## Conclusion

The `@Test` annotation offers a rich set of attributes to customize the execution of your tests. Properly using these options enables you to create reliable, maintainable automated tests that scale from simple checks to complex, data‑driven scenarios.

By mastering these attributes, you can build robust test suites that handle dependencies, parallel execution, data-driven testing, and various execution scenarios effectively.
