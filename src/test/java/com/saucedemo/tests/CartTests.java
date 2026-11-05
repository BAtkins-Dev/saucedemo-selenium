package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    @Test
    public void addOneItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.positiveUserLogin("standard_user", "secret_sauce");
        inventoryPage.addToCartClick("sauce-labs-backpack");

        Assert.assertEquals(inventoryPage.cartBadgeCount(),"1", "Badge count is incorrect");
    }
}
