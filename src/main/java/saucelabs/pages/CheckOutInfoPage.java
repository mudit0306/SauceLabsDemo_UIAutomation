package saucelabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.base.TestBase;

public class CheckOutInfoPage extends TestBase {
    @FindBy(id="first-name")
    WebElement firstName;

    @FindBy(id="last-name")
    WebElement lastName;

    @FindBy(id="postal-code")
    WebElement postalCode;

    @FindBy(id="continue")
    WebElement continueCheckout;

    public CheckOutInfoPage(){
        PageFactory.initElements(driver, this);

    }
    public void fillCheckoutInformationAndClickNext(){
        firstName.sendKeys("test FirsName");
        lastName.sendKeys("test LastName");
        postalCode.sendKeys("123456");
        continueCheckout.click();

    }
}
