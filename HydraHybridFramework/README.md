# Hydra Hybrid Framework - Test Automation Framework

## Project Overview
Hydra Hybrid Framework is a comprehensive, production-ready Selenium WebDriver test automation framework built with Java, TestNG, and the Page Object Model (POM) pattern. It provides a scalable and maintainable solution for web application testing.

## Framework Architecture

### Directory Structure
```
HydraHybridFramework/
├── src/
│   ├── main/java/com/hydra/
│   │   ├── base/
│   │   │   ├── BasePage.java              # Base class for all page objects
│   │   │   ├── BaseTest.java              # Base class for all tests
│   │   │   └── WebDriverFactory.java      # WebDriver initialization
│   │   ├── config/
│   │   │   └── ConfigManager.java         # Configuration management
│   │   ├── pages/
│   │   │   ├── GoogleHomePage.java        # Google search page object
│   │   │   └── LoginPage.java             # Login page object
│   │   └── utils/
│   │       ├── ScreenshotUtils.java       # Screenshot handling
│   │       ├── CommonUtils.java           # Common utility methods
│   │       ├── ExcelUtils.java            # Excel operations for data-driven testing
│   │       ├── JsonUtils.java             # JSON handling
│   │       └── WaitUtils.java             # Wait operations
│   └── test/
│       ├── java/com/hydra/tests/
│       │   ├── GoogleSearchTest.java      # Google search test cases
│       │   └── LoginPageTest.java         # Login page test cases
│       └── resources/
│           ├── properties/
│           │   └── config.properties      # Configuration properties
│           ├── testdata/                  # Test data files
│           └── log4j2.xml                 # Logging configuration
├── pom.xml                                # Maven configuration
├── testng.xml                             # TestNG suite configuration
└── README.md                              # This file

```

## Key Features

### 1. **Page Object Model (POM)**
- Separates test logic from element locators
- Improves maintainability and readability
- Easy to update locators without changing test code

### 2. **Base Classes**
- **BasePage**: Common methods for all page objects (click, type, wait, etc.)
- **BaseTest**: Setup and teardown for all tests
- **WebDriverFactory**: Centralized WebDriver initialization

### 3. **Configuration Management**
- External configuration file (config.properties)
- Support for multiple browsers (Chrome, Firefox, Edge)
- Configurable timeouts and paths

### 4. **Utilities**
- **ScreenshotUtils**: Automatic screenshot capture on test failure
- **ExcelUtils**: Data-driven testing with Excel files
- **CommonUtils**: Common functions (wait, random data generation)
- **JsonUtils**: JSON file handling
- **WaitUtils**: Custom wait implementations

### 5. **Comprehensive Logging**
- Log4j2 configuration with multiple appenders
- Console and file logging
- Error-specific log files
- Rolling file appenders

## Technologies Used

- **Selenium WebDriver 4.15.0** - Web browser automation
- **TestNG 7.8.1** - Test framework
- **Maven** - Build automation
- **Log4j2** - Logging
- **WebDriver Manager** - Automatic driver management
- **Apache POI** - Excel operations
- **GSON** - JSON handling

## Setup Instructions

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

### Installation

1. Clone the repository
```bash
git clone <repository-url>
cd HydraHybridFramework
```

2. Install dependencies
```bash
mvn clean install
```

3. Update configuration (optional)
- Edit `src/test/resources/properties/config.properties`
- Change browser, URLs, timeouts as needed

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific TestNG Suite
```bash
mvn clean test -Dsuites=testng.xml
```

### Run Tests with Specific Browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run Tests in Parallel
Edit testng.xml and modify:
```xml
<suite thread-count="3" parallel="tests">
```

## Usage Examples

### Creating a New Page Object
```java
package com.hydra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.hydra.base.BasePage;

public class DashboardPage extends BasePage {
    
    // Locators
    private By userId = By.id("user-id");
    private By logout = By.id("logout-btn");
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    public void clickLogout() {
        click(logout);
    }
    
    public String getUserId() {
        return getText(userId);
    }
}
```

### Creating a New Test Class
```java
package com.hydra.tests;

import com.hydra.base.BaseTest;
import com.hydra.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    
    @Test(description = "User should see dashboard")
    public void testDashboard() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToURL("https://example.com/dashboard");
        
        String userId = dashboardPage.getUserId();
        Assert.assertNotNull(userId);
    }
}
```

## Configuration Properties

Update `src/test/resources/properties/config.properties`:

```properties
# Application URL
app.url=https://www.google.com

# Browser (chrome, firefox, edge)
browser=chrome

# Timeouts (seconds)
implicit.wait=10
explicit.wait=20
pageload.wait=30

# Screenshot on failure
screenshot.on.failure=true
screenshot.path=screenshots/

# Report path
report.path=reports/
```

## Data-Driven Testing with Excel

```java
// Read Excel file
ExcelUtils.readExcel("testdata/login_data.xlsx", "LoginData");

// Get cell value
String username = ExcelUtils.getCellValue(1, 0);
String password = ExcelUtils.getCellValue(1, 1);

// Use in test
loginPage.login(username, password);
```

## Logging

Logs are stored in the `logs/` directory:
- `test_execution.log` - All test logs
- `test_errors.log` - Error logs only
- `test_rolling.log` - Rolling log file (10MB max)

## Test Reports

After test execution:
- Screenshots: `screenshots/` folder
- Logs: `logs/` folder
- TestNG reports: `target/surefire-reports/` folder

## Best Practices

1. **One assertion per test** - Use multiple test methods instead
2. **Meaningful test names** - Use descriptive names starting with "test"
3. **DRY principle** - Avoid code duplication, use base classes
4. **Wait instead of sleep** - Use WebDriverWait instead of Thread.sleep()
5. **Explicit locators** - Use CSS selectors or XPath for reliability
6. **Logging** - Log important steps for debugging
7. **Error handling** - Provide meaningful error messages

## Troubleshooting

### WebDriver not found
- Check if Chrome/Firefox is installed
- Ensure system PATH includes browser executable

### Tests timing out
- Increase implicit/explicit wait in config.properties
- Check if application is responding

### Screenshot not captured
- Verify screenshot path exists
- Check file permissions

## Contributing

1. Create a new branch for features
2. Follow code conventions
3. Add meaningful commit messages
4. Ensure all tests pass before submitting PR

## License

This project is licensed under MIT License.

## Support

For issues or questions, please create an issue in the repository.

## Changelog

### Version 1.0.0
- Initial framework setup
- POM implementation
- Base classes and utilities
- TestNG integration
- Logging and reporting
