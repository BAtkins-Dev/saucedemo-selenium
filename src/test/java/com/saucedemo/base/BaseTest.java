package com.saucedemo.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.saucedemo.utils.BrowserDriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        BrowserDriverFactory browserDriverFactory = new BrowserDriverFactory();
        driver = browserDriverFactory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
