#!/bin/bash

echo "🚀 Starting Test Automation Framework..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

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
        
        print_status "Opening Allure report in browser..."
        allure open allure-report
    else
        print_error "Failed to generate Allure report"
        exit 1
    fi
else
    print_error "Tests failed. Check the output above for details."
    exit 1
fi
