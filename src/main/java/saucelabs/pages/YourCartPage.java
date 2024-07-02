package saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;

import java.util.List;

public class YourCartPage extends TestBase {

    @FindBy(id = "continue-shopping")
    WebElement continueShopping;

    @FindBy(id = "checkout")
    WebElement continueCheckout;

    @FindBy(xpath = "//div/button[text()='Remove']")
    List<WebElement> removeItemsFromCart;

    public YourCartPage() {
        PageFactory.initElements(driver.get(), this);

    }

    public void clickCheckoutButton() {
        continueCheckout.click();
    }

    public YourCartPage removeAllItemsFromCart() {
        removeItemsFromCart.forEach(WebElement::click);
        ExtentManager.getTest().pass("Removed All Items from Your Cart Page Successfully!");

        return this;
    }

    public void clickContinueShoppingButton() {
        continueShopping.click();
    }


}
