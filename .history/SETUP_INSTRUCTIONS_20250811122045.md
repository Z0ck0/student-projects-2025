# 🚀 Test Automation Framework Setup Instructions

This guide provides step-by-step instructions to set up the Test Automation Framework on both macOS and Windows systems.

## 📋 Prerequisites

- **Java JDK 11 or higher**
- **Maven 3.6 or higher**
- **Git**
- **Chrome, Firefox, or Edge browser**

## 🍎 macOS Setup

### 1. Install Homebrew (if not already installed)

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### 2. Install Java JDK

```bash
brew install openjdk@11
```

### 3. Set JAVA_HOME environment variable

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 11)' >> ~/.zshrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc
```

### 4. Install Maven

```bash
brew install maven
```

### 5. Install Allure

```bash
brew install allure
```

### 6. Verify installations

```bash
java -version
mvn -version
allure --version
```

## 🪟 Windows Setup

### 1. Install Java JDK

- Download JDK 11 from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://adoptopenjdk.net/)
- Run the installer and follow the setup wizard
- Set JAVA_HOME environment variable:
  - Right-click on "This PC" → Properties → Advanced system settings → Environment Variables
  - Add new System Variable: `JAVA_HOME` = `C:\Program Files\Java\jdk-11.x.x`
  - Add `%JAVA_HOME%\bin` to PATH variable

### 2. Install Maven

- Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
- Extract to `C:\Program Files\Apache\maven`
- Add `C:\Program Files\Apache\maven\bin` to PATH variable

### 3. Install Allure

- Download Allure from [GitHub Releases](https://github.com/allure-framework/allure2/releases)
- Extract to `C:\Program Files\allure`
- Add `C:\Program Files\allure\bin` to PATH variable

### 4. Verify installations

```cmd
java -version
mvn -version
allure --version
```

## 🔧 Project Setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd student-projects-2025
```

### 2. Install dependencies

```bash
mvn clean install
```

### 3. Run tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExampleTest

# Run specific test method
mvn test -Dtest=ExampleTest#testHomepageLoads
```

## 📊 Allure Reporting

### Generate and view Allure report

```bash
# macOS/Linux
./run-allure-report.sh

# Windows
run-allure-report.bat
```

### Manual Allure commands

```bash
# Generate report
allure generate allure-results --clean -o allure-report

# Open report in browser
allure open allure-report

# Serve report (for sharing)
allure serve allure-results
```

## 🧪 Running Tests

### Test Execution Options

#### 1. Run all tests

```bash
mvn test
```

#### 2. Run specific test groups

```bash
# Smoke tests
mvn test -Dgroups=smoke

# Regression tests
mvn test -Dgroups=regression
```

#### 3. Run tests with specific browser

```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

#### 4. Run tests in parallel

```bash
mvn test -Dparallel=methods -DthreadCount=4
```

### TestNG XML Configurations

The framework includes several TestNG XML configurations:

- `testng.xml` - Main test suite
- `testngconfigs/cross-browser-tests.xml` - Cross-browser testing
- `testngconfigs/parallel-tests.xml` - Parallel execution
- `testngconfigs/groups-smoke-tests.xml` - Smoke test suite
- `testngconfigs/groups-regression-tests.xml` - Regression test suite

## 🛠️ Troubleshooting

### Common Issues

#### 1. "mvn: command not found"

- Ensure Maven is installed and added to PATH
- Restart terminal/command prompt after installation

#### 2. "java: command not found"

- Verify JAVA_HOME is set correctly
- Ensure Java is in PATH

#### 3. "allure: command not found"

- Install Allure using the appropriate method for your OS
- Restart terminal after installation

#### 4. WebDriver issues

- Update Chrome/Firefox/Edge to latest version
- Ensure ChromeDriver/GeckoDriver/EdgeDriver versions match browser versions

#### 5. Test execution failures

- Check `config.properties` for correct base URL
- Verify browser compatibility
- Check network connectivity

### Debug Mode

Run tests with debug information:

```bash
mvn test -X
```

### Logging

Framework logs are available in:

- Console output during test execution
- `target/surefire-reports/` directory
- Allure report details

## 📁 Project Structure

```
student-projects-2025/
├── src/
│   ├── main/java/                    # Domain classes (initially empty)
│   └── test/
│       ├── java/com/testautomation/
│       │   ├── enums/               # Test enums (BrowserType, SeverityLevel, TestType)
│       │   ├── fixtures/            # Test data providers
│       │   ├── pages/               # Page Object Model classes
│       │   ├── tests/               # Test classes
│       │   └── utilities/           # Utility classes
│       └── resources/
│           ├── config.properties     # Configuration file
│           ├── testng.xml           # Main TestNG configuration
│           └── testngconfigs/       # Additional TestNG configurations
├── pom.xml                          # Maven configuration
├── run-allure-report.sh             # Allure script (macOS/Linux)
├── run-allure-report.bat            # Allure script (Windows)
└── README.md                        # Project documentation
```

## 🔄 Framework Features

- **Multi-browser support** (Chrome, Firefox, Edge, Safari)
- **Page Object Model** design pattern
- **Data-driven testing** with TestNG DataProvider
- **Parallel execution** support
- **Allure reporting** with detailed test results
- **Screenshot capture** on test failure
- **Random data generation** for test data
- **Wait utilities** for element synchronization
- **Configuration management** with properties file
- **Logging** with timestamp and log levels

## 📚 Additional Resources

- [TestNG Documentation](https://testng.org/doc/)
- [Selenium WebDriver](https://selenium.dev/documentation/webdriver/)
- [Allure Framework](https://docs.qameta.io/allure/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

## 🆘 Support

If you encounter issues:

1. Check the troubleshooting section above
2. Review the console output and logs
3. Check the Allure report for detailed test information
4. Verify your environment setup matches the prerequisites
5. Ensure all dependencies are properly installed

---

**Happy Testing! 🎯**
