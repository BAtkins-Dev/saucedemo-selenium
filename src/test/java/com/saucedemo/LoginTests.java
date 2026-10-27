package com.saucedemo;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(driver);

        InventoryPage inventoryPage = loginPage.userLogin("standard_user", "secret_sauce");

        Assert.assertEquals(driver.getCurrentUrl(), inventoryPage.getInventoryUrl(), "Didn't make it to the inventory page");
    }
}
