# Contributing Guidelines

Guidelines for contributing to the Student Projects 2025 test automation framework, including code standards, testing requirements, and repository maintenance.

## Table of Contents

1. [Getting Started](#getting-started)
2. [Code Standards](#code-standards)
3. [Testing Requirements](#testing-requirements)
4. [Documentation Standards](#documentation-standards)
5. [Repository Maintenance](#repository-maintenance)
6. [Review Process](#review-process)

## Getting Started

### Prerequisites

Before contributing, ensure you have:

- **Framework Setup**: Successfully set up the framework following [02-SETUP.md](02-SETUP.md)
- **Understanding**: Read [03-GUIDE.md](03-GUIDE.md) to understand framework patterns
- **Development Environment**: IDE with Java 11+ and Maven support
- **Git Knowledge**: Basic understanding of Git workflows

### Contribution Workflow

1. **Clone the Repository**: Create your own local clone of the project
2. **Create Feature Branch**: Work on features in dedicated branches
3. **Follow Standards**: Adhere to code and documentation standards
4. **Test Thoroughly**: Ensure all tests pass and new functionality is tested
5. **Submit Pull Request**: Create a detailed pull request with clear description

## Code Standards

### Java Coding Standards

#### Naming Conventions

- **Classes**: PascalCase (e.g., `LoginPage`, `UserManagementTest`)
- **Methods**: camelCase (e.g., `performLogin()`, `verifyUserExists()`)
- **Variables**: camelCase (e.g., `userName`, `expectedResult`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DEFAULT_TIMEOUT`, `MAX_RETRY_COUNT`)
- **Packages**: lowercase (e.g., `com.testautomation.core.config`)

#### Code Structure

```java
public class ExampleTest extends BaseTest {

    // Constants first
    private static final String EXPECTED_TITLE = "Example Page";

    // Instance variables
    private ExamplePage examplePage;

    // Setup methods
    @BeforeMethod
    public void setUp() {
        examplePage = new ExamplePage(driver);
    }

    // Test methods
    @Test(description = "Verify example functionality")
    public void testExampleFunctionality() {
        // Arrange
        String expectedResult = "Expected Result";

        // Act
        String actualResult = examplePage.performAction();

        // Assert
        Assert.assertEquals(actualResult, expectedResult,
            "Result should match expected");
    }

    // Helper methods (private)
    private void performSetup() {
        // Setup logic
    }
}
```

#### Import Organization

```java
// Java imports
import java.util.List;
import java.util.concurrent.TimeUnit;

// Third-party imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

// Framework imports
import com.testautomation.tests.base.BaseTest;
import com.testautomation.pages.ExamplePage;
import com.testautomation.utils.common.LoggerUtil;
```

### Framework-Specific Standards

#### Page Object Model

- **Extend BasePage**: All page objects must extend `BasePage`
- **Element Locators**: Keep all locators as private fields
- **Page Methods**: Provide public methods for page interactions
- **Navigation**: Include navigation methods when applicable

```java
public class LoginPage extends BasePage {

    // Element locators (private)
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Public methods for page interactions
    public void enterUsername(String username) {
        sendKeysToElement(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeysToElement(passwordField, password);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    // Business logic methods
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
```

#### Test Classes

- **Extend BaseTest**: All test classes must extend `BaseTest`
- **Descriptive Names**: Use clear, descriptive test method names
- **Single Responsibility**: Each test should verify one specific behavior
- **Proper Annotations**: Use TestNG annotations appropriately

```java
@Test(
    description = "Verify user can login with valid credentials",
    groups = {"smoke", "login"},
    priority = 1
)
public void testUserLoginWithValidCredentials() {
    // Test implementation
}
```

#### Utility Classes

- **Static Methods**: Utility methods should be static when possible
- **Input Validation**: Validate input parameters
- **Error Handling**: Use appropriate exception handling
- **Documentation**: Include JavaDoc for public methods

```java
public class ExampleUtils {

    /**
     * Performs a specific utility operation.
     *
     * @param input the input parameter
     * @return the result of the operation
     * @throws IllegalArgumentException if input is invalid
     */
    public static String performOperation(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        try {
            // Operation logic
            return "Result: " + input;
        } catch (Exception e) {
            LoggerUtil.error("Operation failed: " + e.getMessage(), e);
            throw new RuntimeException("Operation failed", e);
        }
    }
}
```

## Testing Requirements

### Test Coverage Requirements

#### New Features

- **Unit Tests**: Test individual methods and classes
- **Integration Tests**: Test component interactions
- **End-to-End Tests**: Test complete user workflows
- **Edge Cases**: Test boundary conditions and error scenarios

#### Test Quality Standards

- **Descriptive Names**: Test names should clearly describe what is being tested
- **Proper Assertions**: Use appropriate assertion methods with descriptive messages
- **Data Independence**: Tests should not depend on external data or state
- **Cleanup**: Tests should clean up after themselves

### Test Data Management

#### Test Data Creation

```java
@Test(dataProvider = "userData", dataProviderClass = TestDataProvider.class)
public void testWithData(String username, String password, String expectedResult) {
    // Test using provided data
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(username, password);

    // Verify result
    String actualResult = loginPage.getLoginResult();
    Assert.assertEquals(actualResult, expectedResult,
        "Login result should match expected for user: " + username);
}
```

#### Random Data Generation

```java
@Test
public void testWithRandomData() {
    // Generate random test data
    String email = RandomDataGenerator.generateRandomEmail();
    String name = RandomDataGenerator.generateRandomFirstName();

    // Use generated data in test
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.registerUser(name, email);

    // Verify registration
    Assert.assertTrue(registrationPage.isRegistrationSuccessful(),
        "Registration should succeed for: " + email);
}
```

### Test Execution

#### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExampleTest

# Run specific test method
mvn test -Dtest=ExampleTest#testMethod

# Run with specific groups
mvn test -Dgroups=smoke

# Run with specific TestNG suite
mvn test -DsuiteXmlFile=src/test/resources/testng/testng.xml
```

#### Test Results

- **All Tests Must Pass**: Ensure all existing tests pass before submitting
- **New Tests Must Pass**: All new tests must pass consistently
- **Performance**: New tests should not significantly impact execution time
- **Reports**: Verify that test reports are generated correctly

## Documentation Standards

### Code Documentation

#### JavaDoc Requirements

- **Public Methods**: All public methods must have JavaDoc
- **Parameters**: Document all parameters with `@param`
- **Return Values**: Document return values with `@return`
- **Exceptions**: Document exceptions with `@throws`

```java
/**
 * Performs user login with the specified credentials.
 *
 * @param username the username for login
 * @param password the password for login
 * @return true if login is successful, false otherwise
 * @throws IllegalArgumentException if username or password is null
 * @throws WebDriverException if browser interaction fails
 */
public boolean login(String username, String password) {
    // Method implementation
}
```

#### Inline Comments

- **Complex Logic**: Add comments for complex or non-obvious code
- **Business Rules**: Document business logic and requirements
- **Workarounds**: Explain any workarounds or temporary solutions
- **TODO Comments**: Use TODO comments for future improvements

### Documentation Updates

#### Required Updates

- **New Features**: Update relevant documentation when adding features
- **API Changes**: Update API documentation for any interface changes
- **Configuration Changes**: Update configuration documentation
- **Examples**: Add examples for new functionality

#### Documentation Quality

- **Clear Language**: Use clear, concise language
- **Examples**: Include practical examples
- **Consistency**: Maintain consistent formatting and style
- **Accuracy**: Ensure documentation matches actual implementation

## Repository Maintenance

### File Organization

#### Package Structure

- **Follow Existing Structure**: Place new files in appropriate packages
- **Logical Grouping**: Group related functionality together
- **Clear Naming**: Use descriptive package and class names
- **Avoid Deep Nesting**: Keep package hierarchy manageable

#### Resource Organization

- **Configuration Files**: Place in `src/test/resources/config/`
- **Test Data**: Place in `src/test/resources/data/`
- **TestNG Suites**: Place in `src/test/resources/testng/`
- **Report Templates**: Place in `src/test/resources/reports/`

### Version Control

#### Commit Standards

- **Descriptive Messages**: Use clear, descriptive commit messages
- **Atomic Commits**: Make small, focused commits
- **Feature Branches**: Work on features in dedicated branches
- **Regular Commits**: Commit frequently to avoid large changes

#### Commit Message Format

```
type(scope): description

[optional body]

[optional footer]
```

**Types**:

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes
- `refactor`: Code refactoring
- `test`: Test additions or changes
- `chore`: Maintenance tasks

**Examples**:

```
feat(login): add user authentication functionality

fix(driver): resolve WebDriver initialization issue

docs(readme): update setup instructions for Windows

test(login): add comprehensive login test coverage
```

### Dependency Management

#### Maven Dependencies

- **Version Management**: Use consistent version numbers
- **Dependency Updates**: Regularly update dependencies for security
- **Minimal Dependencies**: Only include necessary dependencies
- **Version Compatibility**: Ensure compatibility between dependencies

#### Dependency Review

- **Security**: Check for known security vulnerabilities
- **Licenses**: Ensure dependencies have compatible licenses
- **Maintenance**: Prefer actively maintained dependencies
- **Size**: Consider the impact on project size

## Review Process

### Pull Request Requirements

#### Before Submitting

- **Tests Pass**: All tests must pass locally
- **Code Review**: Self-review your code for quality
- **Documentation**: Update relevant documentation
- **Examples**: Include examples of new functionality

#### Pull Request Content

- **Clear Description**: Describe what the PR accomplishes
- **Related Issues**: Link to related issues or discussions
- **Testing Details**: Explain how the changes were tested
- **Breaking Changes**: Note any breaking changes

### Review Criteria

#### Code Quality

- **Readability**: Code should be easy to read and understand
- **Maintainability**: Code should be easy to maintain and extend
- **Performance**: Code should not introduce performance issues
- **Security**: Code should not introduce security vulnerabilities

#### Testing

- **Coverage**: New functionality should have adequate test coverage
- **Quality**: Tests should be reliable and maintainable
- **Integration**: Tests should integrate well with existing tests
- **Documentation**: Tests should be well-documented

#### Documentation

- **Accuracy**: Documentation should match implementation
- **Completeness**: All new features should be documented
- **Examples**: Documentation should include practical examples
- **Clarity**: Documentation should be clear and easy to follow

### Review Process

#### Review Steps

1. **Automated Checks**: CI/CD pipeline runs automated checks
2. **Code Review**: At least one maintainer reviews the code
3. **Testing**: Changes are tested in the review environment
4. **Documentation**: Documentation is reviewed for accuracy
5. **Approval**: Changes are approved and merged

#### Review Timeline

- **Initial Review**: Within 2-3 business days
- **Follow-up**: Additional reviews within 1-2 business days
- **Final Approval**: Within 1 week for most changes
- **Complex Changes**: May require additional time for thorough review

## Getting Help

### Communication Channels

- **Issues**: Use GitHub issues for bug reports and feature requests
- **Discussions**: Use GitHub discussions for questions and ideas
- **Pull Requests**: Use PR comments for specific code feedback
- **Documentation**: Check existing documentation first

### Resources

- **[02-SETUP.md](02-SETUP.md)**: Setup and environment configuration
- **[03-GUIDE.md](03-GUIDE.md)**: Framework usage and best practices
- **Example Tests**: Review existing tests for patterns
- **TestNG Documentation**: [https://testng.org/doc/](https://testng.org/doc/)
- **Selenium Documentation**: [https://selenium.dev/documentation/](https://selenium.dev/documentation/)

---

**Ready to contribute?** Start by setting up the framework and exploring the existing codebase!

**Questions?** Create an issue or discussion on GitHub.
