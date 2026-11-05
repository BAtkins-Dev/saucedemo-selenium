package com.saucedemo.pages;

import com.saucedemo.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected String baseUrl;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ConfigReader configReader = new ConfigReader();
        baseUrl = configReader.getConfigProperty("baseUrl");
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected void sendKeys(By locator, String textSend) {
        driver.findElement(locator).sendKeys(textSend);
    }

    protected void buttonClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
}
