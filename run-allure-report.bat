@echo off
setlocal enabledelayedexpansion

REM Test Automation Framework - Allure Report Runner
REM This script runs tests, generates Allure reports, and opens them
REM Windows equivalent of run-allure-report.sh

REM Colors for output (Windows 10+ supports ANSI colors)
set "RED=[91m"
set "GREEN=[92m"
set "YELLOW=[93m"
set "BLUE=[94m"
set "NC=[0m"

REM Function to print status messages
:print_status
echo %BLUE%[INFO]%NC% %~1
goto :eof

REM Function to print warning messages
:print_warning
echo %YELLOW%[WARNING]%NC% %~1
goto :eof

REM Function to print error messages
:print_error
echo %RED%[ERROR]%NC% %~1
goto :eof

REM Function to clean up previous test results
:cleanup_previous_results
call :print_status "Cleaning previous test results and preparing for new run..."
if "%~1"=="--archive" (
    call :archive_old_results
)
if exist "allure-results" (
    rmdir /s /q "allure-results" 2>nul
)
if exist "allure-report" (
    rmdir /s /q "allure-report" 2>nul
)
if exist "screenshots" (
    rmdir /s /q "screenshots" 2>nul
)
if exist "logs" (
    rmdir /s /q "logs" 2>nul
)
call :print_status "Previous results cleaned successfully - ready for fresh test run"
goto :eof

REM Function to preserve test results while cleaning reports
:preserve_test_results
call :print_warning "Preserving test results..."
if exist "allure-report" (
    rmdir /s /q "allure-report" 2>nul
)
if exist "screenshots" (
    rmdir /s /q "screenshots" 2>nul
)
if exist "logs" (
    rmdir /s /q "logs" 2>nul
)
call :print_status "Test results preserved, only reports cleaned"
goto :eof

REM Function to archive old test results before cleaning
:archive_old_results
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "timestamp=%dt:~0,8%_%dt:~8,6%"
set "archive_dir=test-results-archive\%timestamp%"
if exist "allure-results" (
    dir "allure-results" /b >nul 2>&1
    if not errorlevel 1 (
        call :print_status "Archiving old test results to %archive_dir%..."
        if not exist "test-results-archive" mkdir "test-results-archive"
        mkdir "%archive_dir%"
        xcopy "allure-results\*" "%archive_dir%\" /s /e /y >nul 2>&1
        call :print_status "Old results archived successfully"
    )
)
goto :eof

REM Function to show current status of test results
:show_status
echo 📊 Current Test Results Status:
echo ================================
if exist "allure-results" (
    dir "allure-results" /b >nul 2>&1
    if not errorlevel 1 (
        for /f %%i in ('dir "allure-results\*.json" /b 2^>nul ^| find /c /v ""') do set "result_count=%%i"
        echo ✅ allure-results/: !result_count! test result files
    ) else (
        echo ❌ allure-results/: No test results found
    )
) else (
    echo ❌ allure-results/: No test results found
)
if exist "allure-report" (
    dir "allure-report" /b >nul 2>&1
    if not errorlevel 1 (
        echo ✅ allure-report/: Report generated
    ) else (
        echo ❌ allure-report/: No report generated
    )
) else (
    echo ❌ allure-report/: No report generated
)
if exist "test-results-archive" (
    for /f %%i in ('dir "test-results-archive" /b 2^>nul ^| find /c /v ""') do set "archive_count=%%i"
    echo 📁 test-results-archive/: !archive_count! archived test runs
)
echo ================================
goto :eof

echo 🚀 Starting Test Automation Framework...

REM Check command line arguments
if "%~1"=="--preserve" (
    call :preserve_test_results
) else if "%~1"=="--archive" (
    call :cleanup_previous_results --archive
) else if "%~1"=="--status" (
    call :show_status
    exit /b 0
) else if "%~1"=="--help" (
    echo Usage: %~nx0 [OPTION]
    echo Options:
    echo   --preserve    Preserve test results, clean only reports
    echo   --archive     Archive old results before cleaning
    echo   --status      Show current status of test results
    echo   --help, -h    Show this help message
    echo   (no args)     Clean all previous results for fresh run
    exit /b 0
) else if "%~1"=="-h" (
    echo Usage: %~nx0 [OPTION]
    echo Options:
    echo   --preserve    Preserve test results, clean only reports
    echo   --archive     Archive old results before cleaning
    echo   --status      Show current status of test results
    echo   --help, -h    Show this help message
    echo   (no args)     Clean all previous results for fresh run
    exit /b 0
) else (
    REM Default behavior: clean all previous results
    call :cleanup_previous_results
)

REM Check if Maven is installed
mvn --version >nul 2>&1
if errorlevel 1 (
    call :print_error "Maven is not installed or not in PATH"
    echo Please install Maven first:
    echo   Windows: Download from https://maven.apache.org/download.cgi
    echo   Or use Chocolatey: choco install maven
    echo   Or use Scoop: scoop install maven
    exit /b 1
)

REM Check if Allure is installed
allure --version >nul 2>&1
if errorlevel 1 (
    call :print_warning "Allure is not installed. Installing Allure..."
    echo Please install Allure manually:
    echo   Windows: Download from https://github.com/allure-framework/allure2/releases
    echo   Or use Chocolatey: choco install allure
    echo   Or use Scoop: scoop install allure
    echo.
    echo After installation, ensure Allure is in your PATH
    pause
    exit /b 1
)

call :print_status "Running tests..."
mvn clean test

if errorlevel 1 (
    call :print_error "Tests failed. Check the output above for details."
    exit /b 1
)

call :print_status "Tests completed successfully!"

call :print_status "Generating Allure report..."
allure generate allure-results --clean -o allure-report

if errorlevel 1 (
    call :print_error "Failed to generate Allure report"
    call :print_status "Checking allure-results directory..."
    if exist "allure-results" (
        dir "allure-results" /b | findstr /n "^" | findstr "^1:" >nul 2>&1
        if not errorlevel 1 (
            dir "allure-results" /b | findstr /n "^" | findstr "^1:"
        )
    )
    exit /b 1
)

call :print_status "Allure report generated successfully!"

REM Verify report was created
if exist "allure-report" (
    dir "allure-report" /b >nul 2>&1
    if not errorlevel 1 (
        call :print_status "Allure report verified successfully!"
        
        call :print_status "Opening Allure report in browser..."
        allure open allure-report
    ) else (
        call :print_error "Allure report directory is empty or missing"
        call :print_status "Checking allure-results directory..."
        if exist "allure-results" (
            dir "allure-results" /b | findstr /n "^" | findstr "^1:" >nul 2>&1
            if not errorlevel 1 (
                dir "allure-results" /b | findstr /n "^" | findstr "^1:"
            )
        )
        exit /b 1
    )
) else (
    call :print_error "Allure report directory is missing"
    call :print_status "Checking allure-results directory..."
    if exist "allure-results" (
        dir "allure-results" /b | findstr /n "^" | findstr "^1:" >nul 2>&1
        if not errorlevel 1 (
            dir "allure-results" /b | findstr /n "^" | findstr "^1:"
        )
    )
    exit /b 1
)

echo.
call :print_status "Test execution completed successfully!"
pause