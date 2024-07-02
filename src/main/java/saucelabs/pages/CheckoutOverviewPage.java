package saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends TestBase {

    @FindBy(id="finish")
    WebElement finishCheckout;

    @FindBy(className="summary_tax_label")
    WebElement totalTaxOnItems;

    @FindBy(className="summary_total_label")
    WebElement totalPriceOfItems;

    @FindBy(className="inventory_item_price")
    List<WebElement> cartItemsPriceList;

    @FindBy(className="inventory_item_name")
    List<WebElement> selectedInventoriesList;

    public static List<String> inventoriesNameListCheckoutPage = new ArrayList<>();

    public void finishCheckout(){
        finishCheckout.click();
    }

    public void captureSelectedItemsFromCheckoutPage(){
        for(WebElement inventory : selectedInventoriesList) {
            inventoriesNameListCheckoutPage.add(inventory.getText());
        }
        ExtentManager.getTest().info("Captured Details of Selected Items from Checkout page");

    }

    public CheckoutOverviewPage(){
        PageFactory.initElements(driver.get(), this);
    }

    public Float getExpectedPriceOfItemsInclTaxes(){

        float subTotal = 0;
        for(WebElement price:cartItemsPriceList){
            subTotal=subTotal+Float.parseFloat(price.getText().split("\\$")[1]);
        }
        String totalTax = totalTaxOnItems.getText().split("\\$")[1];
        Float expectedCalculatedPrice = subTotal+Float.parseFloat(totalTax);
        ExtentManager.getTest().info("Expected Calculated Price: "+expectedCalculatedPrice);

        return expectedCalculatedPrice;

    }

    public Float getActualTotalFromCheckoutPage(){

        String subTotal = totalPriceOfItems.getText().split("\\$")[1];
        ExtentManager.getTest().info("Actual Price on Checkout Page " +subTotal);
        return Float.parseFloat(subTotal);
    }
}
