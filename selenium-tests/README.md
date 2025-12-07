# MERN Auth App - Selenium Automated Tests

This module contains automated Selenium test cases for the MERN Auth Application. It includes 12 comprehensive test cases covering sign-up, sign-in, and page navigation functionality.

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Chromium browser
- ChromeDriver (automatically managed by WebDriverManager)

## Project Structure

```
selenium-tests/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/mernauth/
│       │       ├── tests/       # Test classes
│       │       ├── pages/       # Page Object Model classes
│       │       └── utils/       # Utility classes
│       └── resources/           # Configuration files
├── pom.xml                      # Maven configuration
├── testng.xml                   # TestNG suite configuration
└── README.md                    # This file
```

## Test Coverage

### Test Cases (12 Total)

1. **testHomePageDisplayed** - Verify home page loads with correct title and content
2. **testSignInButtonNavigation** - Verify Sign In button navigates to Sign In page
3. **testSignUpButtonNavigation** - Verify Sign Up button navigates to Sign Up page
4. **testSignUpPageFields** - Verify Sign Up page displays all required fields
5. **testSignInPageFields** - Verify Sign In page displays all required fields
6. **testSignInWithInvalidCredentials** - Verify error message for invalid sign-in credentials
7. **testSignUpWithMismatchedPasswords** - Verify error for mismatched passwords
8. **testSignUpWithShortPassword** - Verify error for passwords less than 6 characters
9. **testSignInPageHasSignUpLink** - Verify navigation from Sign In to Sign Up page
10. **testSignUpPageHasSignInLink** - Verify navigation from Sign Up to Sign In page
11. **testSignInPageTitle** - Verify correct page title on Sign In page
12. **testSignUpPageTitle** - Verify correct page title on Sign Up page

## Page Object Model (POM)

The tests use the Page Object Model design pattern with the following page classes:

- **HomePage** - Home page interactions and verifications
- **SignInPage** - Sign In page interactions and verifications
- **SignUpPage** - Sign Up page interactions and verifications
- **WelcomePage** - Welcome page interactions and verifications

## Utility Classes

- **WebDriverFactory** - Creates WebDriver instances with headless Chrome configuration
- **BaseTest** - Base test class with setUp and tearDown methods

## Running Tests

### 1. Prerequisites Setup

Make sure your MERN app is running:
```bash
# Terminal 1: Backend
cd backend
npm start

# Terminal 2: Frontend
cd frontend
npm start
```

### 2. Run All Tests

```bash
mvn clean test
```

### 3. Run Specific Test Class

```bash
mvn test -Dtest=MernAuthAppTest
```

### 4. Run Specific Test Method

```bash
mvn test -Dtest=MernAuthAppTest#testHomePageDisplayed
```

### 5. Run with Headless Chrome (CI/CD)

Tests are configured to run in headless mode by default. No changes needed.

### 6. View Test Report

After running tests, view the generated report:
```bash
target/surefire-reports/index.html
```

## Headless Chrome Configuration

The tests are configured to run in headless mode suitable for CI/CD environments:

- `--headless` - Run without GUI
- `--no-sandbox` - Disable sandbox (for Docker/Linux)
- `--disable-dev-shm-usage` - Disable shared memory usage
- `--disable-gpu` - Disable GPU acceleration
- `--window-size=1920,1080` - Set window size

This configuration is ideal for Docker containers and Jenkins pipelines.

## Dependencies

Key dependencies managed by Maven:

- **Selenium WebDriver** (4.15.0) - Browser automation
- **WebDriverManager** (5.6.3) - Automatic driver management
- **TestNG** (7.8.1) - Test framework
- **JUnit** (4.13.2) - Assertions and testing
- **REST Assured** (5.3.2) - API testing (for future use)
- **SLF4J** (2.0.9) - Logging

## CI/CD Integration

### Docker Support

The tests can be run in Docker using the Maven Chrome image:

```dockerfile
FROM markhobson/maven-chrome:latest

COPY . /app
WORKDIR /app

RUN mvn clean test
```

### Jenkins Pipeline

Example Jenkinsfile for running tests:

```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
}
```

## Troubleshooting

### Tests timeout
- Increase WebDriverWait timeout in BaseTest.java
- Check if MERN app is running on localhost:3000

### Chrome driver issues
- WebDriverManager should auto-download ChromeDriver
- Clear Maven cache if issues persist: `mvn clean`

### Headless Chrome not working
- Ensure Chrome is installed on the system
- Update Chrome browser to latest version
- Check system resources (RAM, CPU)

## Best Practices

1. **Always run setUp and tearDown** - Tests use @BeforeMethod and @AfterMethod
2. **Use WebDriverWait** - Avoid hard timeouts with implicit waits
3. **Page Object Model** - Locators centralized in page classes
4. **Descriptive test names** - Clear description of what each test does
5. **Headless mode for CI/CD** - Always use for automated pipelines

## Future Enhancements

- [ ] Add tests for successful sign-up and sign-in with valid data
- [ ] Add screenshot capture on test failure
- [ ] Add performance testing
- [ ] Add accessibility testing
- [ ] Integrate with Selenium Grid for parallel execution
- [ ] Add database validation tests

## Author

MERN Auth App - QA Team

## Version

1.0 - Initial release

