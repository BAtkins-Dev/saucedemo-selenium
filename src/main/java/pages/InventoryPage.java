package pages;

import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private final String inventoryUrl = "https://www.saucedemo.com/inventory.html";

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getInventoryUrl() {
        return inventoryUrl;
    }
}
