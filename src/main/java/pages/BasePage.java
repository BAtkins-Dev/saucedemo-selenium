package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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
}
