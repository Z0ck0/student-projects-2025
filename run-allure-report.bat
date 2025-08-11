@echo off
echo 🚀 Starting Test Automation Framework for Windows...

:: Colors for output (Windows 10+ supports ANSI colors)
set "RED=[91m"
set "GREEN=[92m"
set "YELLOW=[93m"
set "NC=[0m"

:: Function to print colored output
call :print_status "Starting test execution and Allure report generation..."

:: Check if Maven is installed
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo %RED%❌ Maven is not installed or not in PATH%NC%
    echo Please install Maven first:
    echo   Download from https://maven.apache.org/download.cgi
    echo   Extract to C:\Program Files\Apache\maven
    echo   Add C:\Program Files\Apache\maven\bin to PATH variable
    pause
    exit /b 1
)

:: Check if Allure is installed
where allure >nul 2>&1
if %errorlevel% neq 0 (
    echo %YELLOW%⚠️  Allure is not installed. Installing Allure...%NC%
    echo Please install Allure manually:
    echo   1. Download from https://github.com/allure-framework/allure2/releases
    echo   2. Extract to C:\Program Files\allure
    echo   3. Add C:\Program Files\allure\bin to PATH variable
    echo   4. Restart command prompt after installation
    pause
    exit /b 1
)

echo %GREEN%✅ Running tests...%NC%
call mvn clean test

if %errorlevel% equ 0 (
    echo %GREEN%✅ Tests completed successfully!%NC%
    
    echo %GREEN%✅ Generating Allure report...%NC%
    call allure generate allure-results --clean -o allure-report
    
    if %errorlevel% equ 0 (
        echo %GREEN%✅ Allure report generated successfully!%NC%
        
        echo %GREEN%✅ Opening Allure report in browser...%NC%
        call allure open allure-report
    ) else (
        echo %RED%❌ Failed to generate Allure report%NC%
        pause
        exit /b 1
    )
) else (
    echo %RED%❌ Tests failed. Check the output above for details.%NC%
    pause
    exit /b 1
)

echo %GREEN%✅ Allure report process completed successfully!%NC%
pause
exit /b 0

:print_status
echo %GREEN%✅ %~1%NC%
goto :eof