# Page Object Manager Pattern

## Overview

The Page Object Manager pattern provides centralized management of all page objects in your test automation framework. This approach improves maintainability, scalability, and test isolation.

## Architecture

```
BaseTest
    ↓
PageObjectManager (manages all page objects)
    ↓
Individual Page Objects (ButtonsPage, ExamplePage, etc.)
```

## Key Benefits

1. **🎯 Centralized Management**: All page objects in one place
2. **🔄 Lazy Initialization**: Page objects created only when needed
3. **🧹 Clean Test Code**: No more manual page object creation
4. **📈 Scalability**: Easy to add new pages
5. **🔒 Test Isolation**: Fresh page objects for each test

## Usage Examples

### Basic Usage in Test Classes

```java
public class ExampleButtons extends BaseTest {

    @Test
    public void testButtonsPage() {
        // Navigate using PageObjectManager
        pages.navigateToButtonsPage();

        // Interact with page using PageObjectManager
        pages.getButtonsPage().clickDoubleClickButton();

        // Verify page state
        Assert.assertTrue(pages.getButtonsPage().isPageLoaded());
    }
}
```

### Navigation Methods

```java
// Navigate using PageObjectManager
pages.navigateToButtonsPage();
pages.navigateToPracticeFormPage();
pages.navigateToTextBoxPage();

// Custom navigation
pages.navigateToCustomPath("custom-page");
pages.navigateToUrl("https://example.com");

// Browser navigation (still direct)
driver.navigate().refresh();
driver.navigate().back();
driver.navigate().forward();
```

### Page Object Access

```java
// Get page objects
ButtonsPage buttonsPage = pages.getButtonsPage();
ExamplePage examplePage = pages.getExamplePage();

// Access page methods
buttonsPage.clickButton();
examplePage.fillForm();
```

## Adding New Pages

### 1. ========== CREATE THE PAGE CLASS ==========

```java
public class NewPage extends BasePage {
    public NewPage(WebDriver driver) {
        super(driver);
    }

    // Page-specific methods
    public void performAction() {
        // Implementation
    }
}
```

### 2. ========== ADD TO PAGE OBJECT MANAGER ==========

```java
public class PageObjectManager {
    private NewPage newPage;

    public NewPage getNewPage() {
        if (newPage == null) {
            newPage = new NewPage(driver);
            LoggerUtil.info("NewPage initialized");
        }
        return newPage;
    }
}
```

### 3. ========== ADD NAVIGATION METHOD ==========

```java
// Add to PageObjectManager:
public void navigateToNewPage() {
    driver.get(ConfigReader.getBaseUrl() + "new-page");
    LoggerUtil.info("Navigated to New Page");
}

// Use in tests:
@Test
public void testNewPage() {
    pages.navigateToNewPage();
    pages.getNewPage().performAction();
}
```

## Best Practices

1. **✅ Always use `pages.getPageName()` instead of direct instantiation**
2. **✅ Use `pages.navigateToPageName()` for all navigation**
3. **✅ Let PageObjectManager handle page object lifecycle**
4. **✅ Add new pages and navigation methods to PageObjectManager**
5. **✅ Keep navigation centralized and consistent**


## Troubleshooting

### Common Issues

1. **Page object not found**: Ensure it's added to PageObjectManager
2. **Driver null**: Check BaseTest.setUp() is called

### Debug Methods

```java
// Check initialized page count
int count = pages.getInitializedPageCount();
LoggerUtil.info("Initialized pages: " + count);

// Reset all pages (useful for debugging)
pages.resetPages();
```

## Future Enhancements

- [ ] Add page object validation
- [ ] Implement page object caching strategies
- [ ] Add page object state management
- [ ] Create page object factory patterns
- [ ] Add page object performance metrics
