# SauceDemo Selenium WebDriver Test Suite

An automated UI test suite for [SauceDemo](https://www.saucedemo.com/), built with [Selenium](https://www.selenium.dev/)
and Java. The project uses the Page Object Model (POM) to keep test logic separate from UI interaction details, and
includes data-driven UI tests.

## Tech Stack

- **Selenium-Java 4.51.0** – browser automation
- **TestNG 7.12.0** - test runner
- **Java 21**
- **Maven with Surefire plugin**

## Features / What It Covers

- **Login** – successful login, and a locked-out user being blocked with the correct error
- **Cart** – adding single and multiple items to a cart and verifying item quantity (badge)

## Design Notes

- **Page Object Model**: Each page (`LoginPage`, `InventoryPage`) encapsulates locators and actions. Pages extend `BasePage`, which provides
shared fields (WebDriver, WebDriverWait, and baseUrl) and helper methods (sendKeys, buttonClick, getText, etc.). Methods
  that navigate to a new page return an instance of that page's object (e.g. `LoginPage.positiveUserLogin()` returns an
  `InventoryPage`), so tests can chain through a flow while staying readable.
- **Utilities**: BrowserDriverFactory for creating WebDriver objects based on browser property in config.properties
  file. ConfigReader for reading from the config.properties file.
- **Data Provider**: Contains a csvReader method for reading the rows of a .csv file.
- **Listeners**: RetryAnalyzer for re-executing failed test methods once. Used by addOneItemToCart test method.
  ScreenshotListener that captures screenshots for failed and skipped runs when RegressionTests.xml suite is executed.
- **Data-driven tests**: LoginTests.negativeLoginTest() utilizes the 'readCSV' data provider (csvReader method) to loop over
  `src/test/resources/negativeLoginTest.csv`, running the same negativeLoginTest() test against multiple input/error
  combinations without duplicating test code.

## Known Issue
- Issue: When executed in a Chrome headless browser, CartTests failed to register the Add to Cart button click at a rate of about 3 times in 100 runs when 
adding an item to the cart on https://saucedemo.com/inventory.html; Remove button did not appear after a 10-second wait. Attempted 
to include a page reload, but the issue persisted. Fresh browser required to successfully execute the Add to Cart click. Cause is undetermined.
- Mitigation: One logged retry, with a screenshot of each failed attempt.

## Project Structure

```
├── src/main/java/com/saucedemo/pages/                        # Page Object Model classes
│   ├── BasePage.java
│   ├── LoginPage.java
│   └── InventoryPage.java
├── src/main/java/com/saucedemo/utils/ 
│   ├── BrowserDriverFactory.java
│   └── ConfigReader.java
├── src/test/
│   ├── java/com/saucedemo/base/BaseTest.java
│   ├── java/com/saucedemo/dataproviders/CSVDataProviders.java
│   ├── java/com/saucedemo/listeners/RetryAnalyzer.java
│   ├── java/com/saucedemo/listeners/ScreenshotListener.java
│   ├── java/com/saucedemo/tests/LoginTests.java
│   ├── java/com/saucedemo/tests/CartTests.java
│   ├── resources/TestSuites/RegressionTests.xml
│   ├── resources/config.properties
│   └── resources/negativeLoginTest.csv
├── target/screenshots <-- created at runtime and deleted by mvn clean
└── pom.xml
```

## Setup

Prerequisites:

- JDK 21
- Maven
- Chrome

1. Clone the repo:
   ```bash
   git clone https://github.com/BAtkins-Dev/saucedemo-selenium.git
   cd saucedemo-selenium
   ```

## Running the Tests

Run the full suite (headless, Chrome): `mvn clean test`

## Roadmap

- ThreadLocal`<WebDriver>` for parallel runs
- REST API tests
- POJOs for the API responses
- Log4j
- GitHub Actions CI

## Notes

- Tests target the live `saucedemo.com` site rather than a local instance, so a network connection is required to run
  them.
- Test code is handwritten. Claude coached, reviewed, and wrote small snippets on request