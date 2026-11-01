package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {
    private String browser;

    public BrowserDriverFactory() {
        ConfigReader configReader = new ConfigReader();
        browser = configReader.getConfigProperty("browser");
    }

    public WebDriver createDriver() {
        switch(browser.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException(browser + " is not supported.");
        }
    }
}
