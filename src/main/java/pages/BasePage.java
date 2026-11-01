package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;

public class BasePage {
    protected WebDriver driver;
    protected String baseUrl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
        driver.findElement(locator).click();
    }

    protected String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
