package saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;

import java.util.*;

public class HomePage extends TestBase {

    @FindBy(xpath = "//div[button[text()='Add to cart']]")
    List<WebElement> addtoCartButtonsList;

    @FindBy(xpath = "//div[button[text()='Remove' or text()='Add to cart']]")
    List<WebElement> addtoCartRemoveButtonsList;

    @FindBy(id = "shopping_cart_container")
    WebElement shoppingCart;

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    List<WebElement> inventoriesList;

    @FindBy(className = "inventory_item_price")
    List<WebElement> cartItemsPriceList;
    public static List<String> inventoryItemsNamesList = new ArrayList<>();

    public HomePage selectItemsOnPage(int countToSelect) {
        // Selecting 0th Element everytime as 'Remove' button replaces 'Add to cart' button, decreasing size of addtoCartButtonsList post every click
        // We need 'Add to cart' in sequence to match with capture item names in 'captureSelectedItemsDetailsOnPage' execution sequence
        for (int i = 0; i < countToSelect; i++) {
            addtoCartButtonsList.get(0).click();
        }
        ExtentManager.getTest().info("Selected " + countToSelect + " items to checkout");
        return this;
    }

    public int selectItemsOnPage(Float budget) {
        ExtentManager.getTest().info("Budget to Select Items on Page - " + budget);
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < cartItemsPriceList.size(); i++) {
            map.put(i, cartItemsPriceList.get(i).getText().split("\\$")[1]);
        }

        List<Float> priceList = new ArrayList<>();

        for (String value : map.values()) {
            priceList.add(Float.valueOf(value));
        }
        Collections.sort(priceList);
        int addedItemscount = 0;
        int maxPrice = 0;
        for (Float price : priceList) {
            maxPrice += price;
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (Float.valueOf(entry.getValue()).equals(price)) {
                    ExtentManager.getTest().info("Selecting Items with Price:" + entry.getKey());
                    addtoCartRemoveButtonsList.get(entry.getKey()).click();
                    addedItemscount += 1;
                    break;
                }
            }
            if (maxPrice > budget) {
                break;
            }
        }
        return addedItemscount;
    }


    public HomePage captureSelectedItemsDetailsFromHomePage(int countToSelect) {
        for (int i = 0; i < countToSelect; i++) {
            inventoryItemsNamesList.add(inventoriesList.get(i).getText());
        }
        ExtentManager.getTest().info("Captured Details of Selected Items from Home page");

        return this;
    }

    public boolean validateCartItems(int expectedCount) {

        return expectedCount == Integer.parseInt(shoppingCart.getText());
    }

    public void clickOnShoppingCart() {
        shoppingCart.click();
    }


    public HomePage() {
        PageFactory.initElements(driver.get(), this);
    }


}
