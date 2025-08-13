#!/bin/bash

# Test Automation Framework - Allure Report Runner
# This script runs tests, generates Allure reports, and opens them

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print status messages
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

# Function to print warning messages
print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

# Function to print error messages
print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to clean up previous test results
cleanup_previous_results() {
    print_status "Cleaning previous test results and preparing for new run..."
    if [ "$1" = "--archive" ]; then
        archive_old_results
    fi
    rm -rf allure-results/* 2>/dev/null || true
    rm -rf allure-report/* screenshots/* logs/* 2>/dev/null || true
    print_status "Previous results cleaned successfully - ready for fresh test run"
}

# Function to preserve test results while cleaning reports
preserve_test_results() {
    print_warning "Preserving test results..."
    rm -rf allure-report/* screenshots/* logs/* 2>/dev/null || true
    print_status "Test results preserved, only reports cleaned"
}

# Function to archive old test results before cleaning
archive_old_results() {
    local timestamp=$(date +"%Y%m%d_%H%M%S")
    local archive_dir="test-results-archive/${timestamp}"
    if [ -d "allure-results" ] && [ "$(ls -A allure-results)" ]; then
        print_status "Archiving old test results to ${archive_dir}..."
        mkdir -p "${archive_dir}"
        cp -r allure-results/* "${archive_dir}/" 2>/dev/null || true
        print_status "Old results archived successfully"
    fi
}

# Function to show current status of test results
show_status() {
    echo "📊 Current Test Results Status:"
    echo "================================"
    if [ -d "allure-results" ] && [ "$(ls -A allure-results)" ]; then
        local result_count=$(find allure-results -name "*.json" | wc -l)
        echo "✅ allure-results/: ${result_count} test result files"
    else
        echo "❌ allure-results/: No test results found"
    fi
    if [ -d "allure-report" ] && [ "$(ls -A allure-report)" ]; then
        echo "✅ allure-report/: Report generated"
    else
        echo "❌ allure-report/: No report generated"
    fi
    if [ -d "test-results-archive" ] && [ "$(ls -A test-results-archive)" ]; then
        local archive_count=$(ls -1 test-results-archive | wc -l)
        echo "📁 test-results-archive/: ${archive_count} archived test runs"
    fi
    echo "================================"
}

echo "🚀 Starting Test Automation Framework..."

# Check command line arguments
if [ "$1" = "--preserve" ]; then
    preserve_test_results
elif [ "$1" = "--archive" ]; then
    cleanup_previous_results --archive
elif [ "$1" = "--status" ]; then
    show_status
    exit 0
elif [ "$1" = "--help" ] || [ "$1" = "-h" ]; then
    echo "Usage: $0 [OPTION]"
    echo "Options:"
    echo "  --preserve    Preserve test results, clean only reports"
    echo "  --archive     Archive old results before cleaning"
    echo "  --status      Show current status of test results"
    echo "  --help, -h    Show this help message"
    echo "  (no args)     Clean all previous results for fresh run"
    exit 0
else
    # Default behavior: clean all previous results
    cleanup_previous_results
fi


# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    print_error "Maven is not installed or not in PATH"
    echo "Please install Maven first:"
    echo "  macOS: brew install maven"
    echo "  Linux: sudo apt-get install maven"
    echo "  Windows: Download from https://maven.apache.org/download.cgi"
    exit 1
fi

# Check if Allure is installed
if ! command -v allure &> /dev/null; then
    print_warning "Allure is not installed. Installing Allure..."
    if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        if command -v brew &> /dev/null; then
            brew install allure
        else
            print_error "Homebrew not found. Please install Homebrew first:"
            echo "  /bin/bash -c \"\$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)\""
            exit 1
        fi
    elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Linux
        sudo apt-add-repository ppa:qameta/allure
        sudo apt-get update
        sudo apt-get install allure
    else
        print_error "Unsupported operating system. Please install Allure manually:"
        echo "  https://docs.qameta.io/allure/#_installing_a_commandline"
        exit 1
    fi
fi

print_status "Running tests..."
mvn clean test

if [ $? -eq 0 ]; then
    print_status "Tests completed successfully!"
    
    print_status "Generating Allure report..."
    allure generate allure-results --clean -o allure-report
    
    if [ $? -eq 0 ]; then
        print_status "Allure report generated successfully!"
        
        # Verify report was created
        if [ -d "allure-report" ] && [ "$(ls -A allure-report)" ]; then
            print_status "Allure report verified successfully!"
            
            print_status "Opening Allure report in browser..."
            allure open allure-report
        else
            print_error "Allure report directory is empty or missing"
            print_status "Checking allure-results directory..."
            ls -la allure-results/ | head -5
            exit 1
        fi
    else
        print_error "Failed to generate Allure report"
        print_status "Checking allure-results directory..."
        ls -la allure-results/ | head -5
        exit 1
    fi
else
    print_error "Tests failed. Check the output above for details."
    exit 1
fi
