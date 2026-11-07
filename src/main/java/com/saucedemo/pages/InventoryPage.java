package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static java.lang.Integer.parseInt;

public class InventoryPage extends BasePage {
    private final String inventoryUrl = baseUrl + "inventory.html";
    private final By cartBadgeLocator = By.xpath("//div[@id='shopping_cart_container']/a/span");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getInventoryUrl() {
        return inventoryUrl;
    }

    public String getCurrentUrl() {
        return getCurrentPageUrl();
    }

    public void addToCartClick(String item) {
        By addToCartLocator = By.id("add-to-cart-" + item);
        By removeFromCartLocator = By.id("remove-" + item);

        try {
            buttonClick(addToCartLocator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(removeFromCartLocator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Add to cart did not register for '" + item + "'.", e);
        }
    }

    public int cartBadgeCount() {
        return parseInt(getText(cartBadgeLocator));
    }

    public void addMultipleItemsToCart(List<String> items) {
        for (String item : items) {
            addToCartClick(item);
        }
    }
}
