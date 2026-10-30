package com.saucedemo;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.CSVDataProviders;

public class LoginTests extends BaseTest {
    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(driver);

        InventoryPage inventoryPage = loginPage.positiveUserLogin("standard_user", "secret_sauce");

        Assert.assertEquals(inventoryPage.getCurrentUrl(), inventoryPage.getInventoryUrl(), "Didn't make it to the inventory page");
    }

    @Test(dataProvider = "readCSV", dataProviderClass = CSVDataProviders.class)
    public void negativeLoginTest(String testNumber, String testDescription, String username, String password, String url, String expectedMessage) {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("Test number " + testNumber + ": " + testDescription);
        loginPage.generalUserLogin(username, password);

        Assert.assertEquals(loginPage.getCurrentUrl(), url, "Test number " + testNumber + ": Not on the login page");

        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Test number " + testNumber + ": Incorrect error message");
    }
}
