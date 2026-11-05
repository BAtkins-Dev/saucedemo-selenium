package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverFactory {
    private final String browser;
    private final boolean headlessBrowser;

    public BrowserDriverFactory() {
        ConfigReader configReader = new ConfigReader();
        browser = configReader.getConfigProperty("browser");
        headlessBrowser = Boolean.parseBoolean(configReader.getConfigProperty("headless"));
    }

    public WebDriver createDriver() {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headlessBrowser) {
                    chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headlessBrowser) {
                    firefoxOptions.addArguments("-headless", "--width=1920", "--height=1080");
                }
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headlessBrowser) {
                    edgeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException(browser + " is not supported.");
        }
    }
}
