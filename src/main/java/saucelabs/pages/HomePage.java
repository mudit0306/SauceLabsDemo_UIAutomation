package saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.base.TestBase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends TestBase {

    @FindBy(xpath="//div/button[text()='Add to cart']")
    List<WebElement> addtoCartButtonsList;

    @FindBy(id="shopping_cart_container")
    WebElement shoppingCart;

    @FindBy(xpath="//div[@data-test='inventory-item-name']")
    List<WebElement> inventoriesList;

    public static List<String> inventoryItemsNamesList= new ArrayList<>();

    public HomePage selectItemsOnPage(int countToSelect){
        // Selecting 0th Element everytime as 'Remove' button replaces 'Add to cart' button, decreasing size of addtoCartButtonsList post every click
        // We need 'Add to cart' in sequence to match with capture item names in 'captureSelectedItemsDetailsOnPage' execution sequence
        for(int i=0;i<countToSelect;i++){
            addtoCartButtonsList.get(0).click();
        }
        addInfoLog("Selected " +countToSelect +" items to checkout");
        return this;
    }

    public HomePage captureSelectedItemsDetailsFromHomePage(int countToSelect){
        for(int i=0;i<countToSelect;i++){
            inventoryItemsNamesList.add(inventoriesList.get(i).getText());
        }
        addInfoLog("Captured Details of Selected Items from Home page");
        return this;
    }

    public boolean validateCartItems(int expectedCount){

        return expectedCount==Integer.parseInt(shoppingCart.getText());
    }

    public void clickOnShoppingCart(){
        shoppingCart.click();
    }


    public HomePage(){
        PageFactory.initElements(driver, this);
    }



}
