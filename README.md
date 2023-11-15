**DemoQA Automated UI Testing (Work in Progress)**

**Introduction**
Welcome to the "demoqa-automated-ui-testing-wip" repository, an ongoing project dedicated to automating tests for the DemoQA website.  This project is a work in progress, focusing on implementing robust test cases using Java and the Page Object Model (POM) design pattern.


**Page Object Model (POM) Design Pattern**
**BasePage:** An abstract class serving as a template for other page classes, promoting inheritance, and code reusability.


Test Classes
TextBoxTest
Tests:
Valid data submission with detailed result verification.
Acceptance of special characters in form submission.
Ensures no visible change occurs after an empty submission.
Valid and invalid email submissions with result validation.
RadioButtonTest
Tests:
Success message verification for different radio button selections.
Checking the disabled state of a radio button.
Style and Patterns
Abstraction and Code Reusability
BasePage: Promotes code reusability through abstraction, simplifying the complexity of modeling different entities.
@FindBy - PageFactory
Utilizes @FindBy annotations and PageFactory initialization for clear mapping between web elements and Java representations, enhancing code readability.
Additional Components
BaseTest
Purpose: Contains common setup and teardown procedures for test classes.
Utilities
Purpose: Provides utility methods for common functionalities.
Work in Progress
This project is actively evolving. Expect ongoing additions, improvements, and additional test cases. Feel free to explore the code, run tests, and contribute to the project's development. Happy testing!
