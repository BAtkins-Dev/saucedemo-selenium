package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By actualErrorMessage = By.xpath("//h3[@data-test='error']");
    private final String loginUrl = "https://saucedemo.com/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openUrl() {
        openUrl(loginUrl);
    }

    public InventoryPage positiveUserLogin(String username, String password) {
        generalUserLogin(username, password);
        return new InventoryPage(driver);
    }

    public void generalUserLogin(String username, String password) {
        openUrl();
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        buttonClick(loginButton);
    }

    public String getCurrentUrl() {
        return getCurrentPageUrl();
    }

    public String getErrorMessage() {
        return getText(actualErrorMessage);
    }
}
