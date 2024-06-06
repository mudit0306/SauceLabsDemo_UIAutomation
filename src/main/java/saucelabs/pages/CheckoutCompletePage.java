package saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.base.TestBase;

public class CheckoutCompletePage extends TestBase {

    @FindBy(id="back-to-products")
    WebElement backHomeButton;

    @FindBy(id="checkout_complete_container")
    WebElement checkoutComplete;

    @FindBy(className="title")
    WebElement checkoutCompletePageTitle;

    public CheckoutCompletePage(){
        PageFactory.initElements(driver, this);
    }

    public String getCheckoutcompleteMessage(){

        return checkoutComplete.getText();
    }
    public String getCheckoutCompletePageTitle(){
         return checkoutCompletePageTitle.getText();

    }


    public WebElement getBackHomeButton(){
        return backHomeButton;

    }
}
