# 🚀 Quick Start Guide - Test Automation Framework

This guide will get you up and running with the Test Automation Framework in under 5 minutes!

## 📋 Prerequisites

- **Java JDK 11+** installed
- **Maven 3.6+** installed
- **Git** installed
- **Chrome/Firefox/Edge** browser installed

## ⚡ Quick Setup

### 1. Clone and Navigate

```bash
git clone <your-repo-url>
cd student-projects-2025
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Run Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExampleTest

# Run with specific browser
mvn test -Dbrowser=edge
```

### 4. Generate Allure Report

```bash
# macOS/Linux
./run-allure-report.sh

# Windows
run-allure-report.bat
```

## 🎯 Quick Test Execution

### Run Smoke Tests

```bash
mvn test -Dgroups=smoke
```

### Run Regression Tests

```bash
mvn test -Dgroups=regression
```

### Run Tests in Parallel

```bash
mvn test -Dparallel=methods -DthreadCount=4
```

## 🔧 Quick Configuration

Edit `src/test/resources/config.properties`:

```properties
# Change default browser
browser.default=edge

# Enable headless mode
browser.headless=true

# Change base URL
base.url=https://your-website.com/
```

## 📊 Quick Results

After running tests, find results in:

- **Test Results**: `target/surefire-reports/`
- **Allure Results**: `allure-results/`
- **Screenshots**: `screenshots/`
- **Allure Report**: `allure-report/`

## 🆘 Quick Troubleshooting

### Common Issues:

1. **"mvn: command not found"**

   - Install Maven: `brew install maven` (macOS) or download from [Maven website](https://maven.apache.org/)

2. **"java: command not found"**

   - Install Java JDK 11+: `brew install openjdk@11` (macOS)

3. **WebDriver issues**

   - Update your browser to latest version
   - Framework uses Selenium Manager (auto-downloads drivers)

4. **Tests failing**
   - Check `config.properties` for correct base URL
   - Verify browser compatibility

## 📚 Next Steps

- Read `SETUP_INSTRUCTIONS.md` for detailed setup
- Check `TEMPLATE_GUIDE.md` for framework usage patterns
- Explore `src/test/java/com/testautomation/tests/examples/` for test examples

## 🎉 You're Ready!

Your test automation framework is now running! Start writing tests in the `examples` package and customize for your needs.

**Happy Testing! 🎯**
