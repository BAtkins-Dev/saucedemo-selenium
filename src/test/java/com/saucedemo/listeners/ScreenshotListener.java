package com.saucedemo.listeners;

import com.saucedemo.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult tr) {
        BaseTest testObject = (BaseTest) tr.getInstance();
        WebDriver driver = testObject.getDriver();

        takeScreenshotCommon(driver, tr);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        BaseTest testObject = (BaseTest) tr.getInstance();
        WebDriver driver = testObject.getDriver();

        takeScreenshotCommon(driver, tr);
   }

    private void takeScreenshotCommon(WebDriver driver, ITestResult tr) {
        if (driver == null) {
            System.out.println("No driver for " + tr.getName() + ", skipping screenshot.");
            return;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
            Path screenshotDir = Paths.get(System.getProperty("user.dir") + "/target/screenshots");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            Path destination = Paths.get(screenshotDir + "/" + tr.getName() + "-"+ timeStamp + ".png");
            Files.createDirectories(screenshotDir);
            Files.copy(screenshotFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
        }
    }
}
