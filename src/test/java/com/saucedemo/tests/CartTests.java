package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.listeners.RetryAnalyzer;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTests extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addOneItemToCart() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.positiveUserLogin("standard_user", "secret_sauce");
        inventoryPage.addToCartClick("sauce-labs-backpack");

        Assert.assertEquals(inventoryPage.cartBadgeCount(), 1, "Badge count is incorrect");
    }

    @Test
    public void addThreeItemsToCart() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.positiveUserLogin("standard_user", "secret_sauce");
        List<String> cartItems = List.of("sauce-labs-backpack", "sauce-labs-bike-light", "sauce-labs-fleece-jacket");
        inventoryPage.addMultipleItemsToCart(cartItems);
        Assert.assertEquals(inventoryPage.cartBadgeCount(), cartItems.size(), "Badge count is incorrect");
    }
}
