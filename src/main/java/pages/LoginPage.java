package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final String loginUrl = "https://saucedemo.com/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openUrl() {
        openUrl(loginUrl);
    }

    public InventoryPage userLogin(String username, String password) {
        openUrl();
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        buttonClick(loginButton);
        return new InventoryPage(driver);
    }
}
