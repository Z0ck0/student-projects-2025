# Setup Guide - Test Automation Framework

Complete environment setup and first-run instructions for the Student Projects 2025 test automation framework across all major platforms.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Platform-Specific Setup](#platform-specific-setup)
3. [Framework Setup](#framework-setup)
4. [First Test Run](#first-test-run)
5. [Configuration](#configuration)
6. [Troubleshooting](#troubleshooting)

## Prerequisites

### System Requirements

- **Operating System**: Windows 10+, macOS 10.14+, or Linux (Ubuntu 18.04+)
- **Memory**: Minimum 4GB RAM (8GB+ recommended)
- **Storage**: 2GB free disk space
- **Network**: Internet connection for dependency downloads

### Required Software

- **Java JDK 11+** - Modern Java runtime environment
- **Maven 3.6+** - Build and dependency management
- **Git 2.0+** - Version control system
- **Modern Browser** - Chrome, Firefox, Edge, or Safari

## Platform-Specific Setup

### Windows Setup

#### Install Java JDK

1. **Download Java JDK 11+**:

   - Visit [Eclipse Temurin](https://adoptium.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
   - Download Windows x64 installer
   - Run the installer as Administrator

2. **Set JAVA_HOME Environment Variable**:

   ```cmd
   # Open Command Prompt as Administrator
   setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-11.0.21.9-hotspot" /M
   setx PATH "%PATH%;%JAVA_HOME%\bin" /M
   ```

3. **Verify Installation**:
   ```cmd
   java -version
   javac -version
   echo %JAVA_HOME%
   ```

#### Install Maven

1. **Download Maven**:

   - Visit [Maven Downloads](https://maven.apache.org/download.cgi)
   - Download Binary zip archive (apache-maven-3.9.5-bin.zip)

2. **Extract and Configure**:

   ```cmd
   # Extract to C:\Program Files\Apache\maven
   setx MAVEN_HOME "C:\Program Files\Apache\maven" /M
   setx PATH "%PATH%;%MAVEN_HOME%\bin" /M
   ```

3. **Verify Installation**:
   ```cmd
   mvn -version
   ```

#### Install Git

1. **Download Git**:

   - Visit [Git for Windows](https://git-scm.com/download/win)
   - Download and run the installer
   - Use default settings during installation

2. **Verify Installation**:
   ```cmd
   git --version
   ```

#### Install Browsers

- **Chrome**: [Download here](https://www.google.com/chrome/)
- **Firefox**: [Download here](https://www.mozilla.org/firefox/)
- **Edge**: [Download here](https://www.microsoft.com/edge/)

### macOS Setup

#### Install Java JDK

1. **Using Homebrew (Recommended)**:

   ```bash
   # Install Homebrew if not already installed
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

   # Install Java JDK 11+
   brew install openjdk@11

   # Link to system Java
   sudo ln -sfn /opt/homebrew/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk
   ```

2. **Set JAVA_HOME**:

   ```bash
   # Add to ~/.zshrc or ~/.bash_profile
   echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 11)' >> ~/.zshrc
   echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
   source ~/.zshrc
   ```

3. **Verify Installation**:
   ```bash
   java -version
   javac -version
   echo $JAVA_HOME
   ```

#### Install Maven

```bash
brew install maven
mvn -version
```

#### Install Git

```bash
brew install git
git --version
```

#### Install Browsers

- **Chrome**: `brew install --cask google-chrome`
- **Firefox**: `brew install --cask firefox`
- **Safari**: Pre-installed on macOS

### Linux Setup (Ubuntu/Debian)

#### Install Java JDK

1. **Update Package List**:

   ```bash
   sudo apt update
   sudo apt upgrade -y
   ```

2. **Install Java JDK 11+**:

   ```bash
   sudo apt install openjdk-11-jdk -y
   ```

3. **Set JAVA_HOME**:

   ```bash
   echo 'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64' >> ~/.bashrc
   echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
   source ~/.bashrc
   ```

4. **Verify Installation**:
   ```bash
   java -version
   javac -version
   echo $JAVA_HOME
   ```

#### Install Maven

```bash
sudo apt install maven -y
mvn -version
```

#### Install Git

```bash
sudo apt install git -y
git --version
```

#### Install Browsers

- **Chrome**: [Download .deb package](https://www.google.com/chrome/)
- **Firefox**: `sudo apt install firefox -y`
- **Edge**: [Download .deb package](https://www.microsoft.com/edge/)

## Framework Setup

### Step 1: Clone Repository

```bash
# Clone the repository
git clone <repository-url>
cd student-projects-2025

# Verify the structure
ls -la
```

**Expected Structure**:

```
student-projects-2025/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/testautomation/
│       │       ├── core/           # Core framework components
│       │       ├── enums/          # Framework constants
│       │       ├── fixtures/       # Test data providers
│       │       ├── pages/          # Page Object Model
│       │       ├── tests/          # Test implementations
│       │       └── utils/          # Utility classes
│       └── resources/
│           ├── config/             # Configuration files
│           ├── reports/            # Report templates
│           └── testng/             # TestNG configurations
├── pom.xml                         # Maven configuration
├── README.md                       # Project overview
└── run-allure-report.sh           # Report generation script
```

### Step 2: Install Dependencies

```bash
# Install Maven dependencies
mvn clean install

# Verify compilation
mvn compile
```

**Expected Output**:

```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
[INFO] Finished at: 2025-XX-XX
```

### Step 3: Verify Configuration

1. **Check Configuration File**:

   ```bash
   # Verify config file exists
   ls -la src/test/resources/config/

   # View configuration
   cat src/test/resources/config/config.properties
   ```

2. **Verify TestNG Configuration**:

   ```bash
   # Check TestNG suite
   ls -la src/test/resources/testng/

   # View main suite
   cat src/test/resources/testng/testng.xml
   ```

## First Test Run

### Step 1: Run Simple Test

```bash
# Run a basic navigation test
mvn test -Dtest=ExampleTest#testBasicNavigation
```

**Expected Output**:

```
[INFO] Running TestSuite
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] Results:
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Step 2: Run All Tests

```bash
# Run complete test suite
mvn test
```

**Expected Output**:

```
[INFO] Running TestSuite
[INFO] Tests run: X, Failures: 0, Errors: 0, Skipped: 0
[INFO] Results:
[INFO] Tests run: X, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Step 3: Generate Reports

```bash
# Generate Allure report
./run-allure-report.sh  # macOS/Linux
run-allure-report.bat   # Windows

# Open report
allure open allure-report
```

## Configuration

### Framework Configuration

Edit `src/test/resources/config/config.properties`:

```properties
# Browser Configuration
browser.default=chrome
browser.headless=false
browser.implicitWait=10
browser.explicitWait=20
browser.pageLoadTimeout=30

# Application Configuration
base.url=https://demoqa.com/
test.data.path=src/test/resources/data/

# Framework Configuration
screenshot.enabled=true
screenshot.directory=screenshots/
parallel.enabled=true
parallel.threadCount=4

# Retry Configuration
retry.maxCount=2
```

### TestNG Configuration

Edit `src/test/resources/testng/testng.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Student Projects 2025 Test Suite" parallel="classes" thread-count="4">
    <test name="Example Tests">
        <classes>
            <class name="com.testautomation.tests.examples.ExampleTest"/>
        </classes>
    </test>
</suite>
```

## Troubleshooting

### Common Issues and Solutions

#### Java Not Found

**Problem**: `'java' is not recognized as an internal or external command`

**Solutions**:

- **Windows**: Restart Command Prompt after setting environment variables
- **macOS/Linux**: Restart terminal or run `source ~/.zshrc`
- **Verify**: Check `JAVA_HOME` and `PATH` environment variables

#### Maven Not Found

**Problem**: `'mvn' is not recognized as an internal or external command`

**Solutions**:

- **Windows**: Restart Command Prompt after setting environment variables
- **macOS/Linux**: Restart terminal or run `source ~/.zshrc`
- **Verify**: Check `MAVEN_HOME` and `PATH` environment variables

#### WebDriver Issues

**Problem**: Browser doesn't start or crashes

**Solutions**:

- **Update Browser**: Install latest browser version
- **Check Compatibility**: Verify WebDriver version compatibility
- **Clear Cache**: Clear browser cache and cookies
- **Check Permissions**: Ensure browser has necessary permissions

#### Configuration File Not Found

**Problem**: `Configuration file not found: config.properties`

**Solution**: Verify file location:

```bash
# Check if config file exists
ls -la src/test/resources/config/config.properties

# If missing, create it with default values
mkdir -p src/test/resources/config
touch src/test/resources/config/config.properties
```

#### Import Errors

**Problem**: `The import cannot be resolved`

**Solution**: Update import statements to use new package structure:

```java
// Old imports (don't work)
import com.testautomation.utilities.ConfigReader;

// New imports (use these)
import com.testautomation.core.config.ConfigReader;
```

#### Test Failures

**Problem**: Tests fail with element not found or timing issues

**Solutions**:

- **Check URL**: Verify `base.url` in configuration
- **Add Waits**: Use explicit waits instead of `Thread.sleep()`
- **Check Elements**: Verify element locators are correct
- **Browser Issues**: Try different browser or headless mode

### Debug Mode

Enable debug logging for troubleshooting:

```bash
# Run with debug logging
mvn test -Dtest=ExampleTest -X

# Check logs
tail -f target/surefire-reports/testng-results.xml
```

## Verification Checklist

### Pre-Setup Verification

- [ ] **Java JDK 11+** installed and `JAVA_HOME` set
- [ ] **Maven 3.6+** installed and in `PATH`
- [ ] **Git 2.0+** installed and configured
- [ ] **Modern browser** installed (Chrome/Firefox/Edge/Safari)
- [ ] **Internet connection** available for dependencies

### Post-Setup Verification

- [ ] **Repository cloned** successfully
- [ ] **Dependencies installed** without errors
- [ ] **Framework compiles** successfully
- [ ] **Simple test runs** and passes
- [ ] **All tests run** without errors
- [ ] **Reports generate** successfully
- [ ] **Configuration files** accessible and editable

### Framework Features Verification

- [ ] **Page Object Model** working correctly
- [ ] **Configuration management** functioning
- [ ] **Exception handling** working as expected
- [ ] **Test listeners** tracking execution
- [ ] **Utility classes** accessible and functional
- [ ] **Screenshot capture** working on failures
- [ ] **Logging system** providing detailed output

## Next Steps

### Immediate Actions

1. **Explore the Framework**: Navigate through the organized package structure
2. **Run Examples**: Execute the provided example tests
3. **Modify Tests**: Make small changes to understand the patterns
4. **Read Documentation**: Review `03-GUIDE.md` for usage patterns

### Learning Path

1. **Week 1**: Understand basic structure and run examples
2. **Week 2**: Create simple page objects and tests
3. **Week 3**: Implement data-driven testing (Optional)
4. **Week 4**: Add custom utilities and extend framework (Optional)

---

**Need Help?** Check the troubleshooting section above or create an issue in the repository.

**Ready to start testing?** See [03-GUIDE.md](03-GUIDE.md) for framework usage patterns and examples.
