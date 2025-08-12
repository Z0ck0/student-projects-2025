# Configuration Guide

This guide explains how to configure the test automation framework for your specific project and environment.

## Table of Contents

1. [Configuration Files](#configuration-files)
2. [Project-Specific Configuration](#project-specific-configuration)
3. [Environment-Specific Configuration](#environment-specific-configuration)
4. [Command Line Overrides](#command-line-overrides)
5. [Environment Variables](#environment-variables)
6. [Maven Profiles](#maven-profiles)
7. [Configuration Best Practices](#configuration-best-practices)

## Configuration Files

### Default Configuration (`config.properties`)

The main configuration file `src/test/resources/config/config.properties` contains default values that work for most projects. **Do not modify this file** - it serves as a template and reference.

### Project Configuration (`project-config.properties`)

Create this file to override default values for your specific project:

```bash
# Copy the template
cp src/test/resources/config/project-config-template.properties \
   src/test/resources/config/project-config.properties

# Edit with your project values
nano src/test/resources/config/project-config.properties
```

## Project-Specific Configuration

### 1. Base URL Configuration

**Default:** `https://example.com/`  
**Your Project:** `https://yourdomain.com/`

```properties
# In project-config.properties
base.url=https://yourdomain.com/
```

### 2. Application Details

```properties
app.name=Your Application Name
app.version=1.0.0
```

### 3. Browser Preferences

```properties
# Preferred browser for your project
browser.default=firefox

# Browser window size
browser.width=1366
browser.height=768
```

### 4. Timeout Adjustments

```properties
# Adjust based on your application's performance
timeout.implicit=30
timeout.explicit=30
timeout.pageLoad=90
```

## Environment-Specific Configuration

### Environment Variables

Set different base URLs for different environments:

```properties
# Development
dev.base.url=https://dev.yourdomain.com/

# QA/Testing
qa.base.url=https://qa.yourdomain.com/

# Staging
staging.base.url=https://staging.yourdomain.com/

# Production
prod.base.url=https://yourdomain.com/
```

### Environment Selection

```properties
# Set the current environment
environment=qa
```

## Command Line Overrides

Override any configuration value at runtime using Maven system properties:

### Basic Overrides

```bash
# Override base URL
mvn test -Dbase.url=https://yourdomain.com/

# Override browser
mvn test -Dbrowser.default=firefox

# Override timeouts
mvn test -Dtimeout.implicit=30 -Dtimeout.explicit=30
```

### Multiple Overrides

```bash
mvn test \
  -Dbase.url=https://yourdomain.com/ \
  -Dbrowser.default=firefox \
  -Dtimeout.implicit=30 \
  -Dparallel.enabled=false
```

### Common Override Examples

```bash
# Run against staging environment
mvn test -Dbase.url=https://staging.yourdomain.com/

# Run with specific browser and headless mode
mvn test -Dbrowser.default=chrome -Dbrowser.headless=true

# Disable parallel execution for debugging
mvn test -Dparallel.enabled=false

# Run with custom timeouts
mvn test -Dtimeout.implicit=60 -Dtimeout.explicit=60
```

## Environment Variables

Set environment variables for persistent configuration:

### Unix/Linux/macOS

```bash
# Set in your shell profile (.bashrc, .zshrc, etc.)
export BASE_URL=https://yourdomain.com/
export BROWSER_DEFAULT=firefox
export TIMEOUT_IMPLICIT=30

# Or set for current session
export BASE_URL=https://yourdomain.com/
mvn test
```

### Windows

```cmd
# Set for current session
set BASE_URL=https://yourdomain.com/
set BROWSER_DEFAULT=firefox
mvn test

# Or set permanently in System Properties
```

## Maven Profiles

Create profiles in your `pom.xml` for different environments:

### Profile Configuration

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <base.url>https://dev.yourdomain.com/</base.url>
            <browser.default>chrome</browser.default>
            <parallel.enabled>false</parallel.enabled>
        </properties>
    </profile>

    <profile>
        <id>qa</id>
        <properties>
            <base.url>https://qa.yourdomain.com/</base.url>
            <browser.default>firefox</browser.default>
            <parallel.enabled>true</parallel.enabled>
        </properties>
    </profile>

    <profile>
        <id>staging</id>
        <properties>
            <base.url>https://staging.yourdomain.com/</base.url>
            <browser.default>chrome</browser.default>
            <parallel.enabled>true</parallel.enabled>
        </properties>
    </profile>
</profiles>
```

### Using Profiles

```bash
# Run with specific profile
mvn test -Pqa

# Run with profile and additional overrides
mvn test -Pqa -Dbrowser.default=firefox
```

## Configuration Best Practices

### 1. **Never Modify Default Config**

- Keep `config.properties` unchanged
- Use `project-config.properties` for project-specific values
- Use command line overrides for temporary changes

### 2. **Environment Management**

- Use different profiles for different environments
- Set sensitive data via environment variables
- Document environment-specific configurations

### 3. **Team Collaboration**

- Commit `project-config.properties` to your repository
- Document configuration requirements
- Use consistent naming conventions

### 4. **Security Considerations**

- Never commit sensitive data (passwords, API keys)
- Use environment variables for secrets
- Consider using encrypted configuration files

## Configuration Hierarchy

Configuration values are resolved in this order (highest priority first):

1. **Command line overrides** (`-Dproperty=value`)
2. **Environment variables** (`BASE_URL`, `BROWSER_DEFAULT`)
3. **Project configuration** (`project-config.properties`)
4. **Default configuration** (`config.properties`)

## Troubleshooting

### Common Issues

**Configuration not taking effect:**

- Check file paths and names
- Verify property names match exactly
- Check for typos in values

**Environment variables not working:**

- Restart your terminal/IDE
- Check variable naming (use underscores)
- Verify variable is exported

**Maven profiles not working:**

- Check profile ID matches exactly
- Verify profile is activated
- Check XML syntax in pom.xml

### Debug Configuration

Enable debug logging to see which configuration values are being used:

```bash
mvn test -Dlogging.level=DEBUG
```

## Example Project Setup

### Step 1: Create Project Configuration

```bash
cp src/test/resources/config/project-config-template.properties \
   src/test/resources/config/project-config.properties
```

### Step 2: Edit Project Configuration

```properties
# project-config.properties
base.url=https://myapp.company.com/
app.name=My Company App
app.version=2.1.0
browser.default=firefox
timeout.implicit=30
environment=qa
```

### Step 3: Run Tests

```bash
# Basic run
mvn test

# With overrides
mvn test -Dbrowser.default=chrome -Dtimeout.implicit=45

# With profile
mvn test -Pstaging
```

## Summary

- **Use `project-config.properties`** for project-specific settings
- **Use command line overrides** for temporary changes
- **Use Maven profiles** for environment-specific configurations
- **Never modify** the default `config.properties` file
- **Document** your configuration choices for team members

This approach ensures your framework is flexible, maintainable, and suitable for any project or environment.
