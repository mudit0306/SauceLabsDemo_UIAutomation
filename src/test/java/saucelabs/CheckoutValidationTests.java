package saucelabs;

import com.aventstack.extentreports.ExtentReports;
import org.testng.Assert;
import org.testng.annotations.*;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;
import saucelabs.pages.*;

public class CheckoutValidationTests extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    YourCartPage yourCartPage;
    CheckOutInfoPage checkOutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage;
    ExtentReports extent;

    @BeforeClass
    public void testSetup() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        yourCartPage = new YourCartPage();
        checkOutInfoPage = new CheckOutInfoPage();
        checkoutOverviewPage = new CheckoutOverviewPage();

    }

    @Test
    public void validateTotalPriceOfItemsOnCheckoutTest() {
        loginPage.login();

        homePage.selectItemsOnPage(3).clickOnShoppingCart();

        yourCartPage.clickCheckoutButton();

        checkOutInfoPage.fillCheckoutInformationAndClickNext();

        Assert.assertEquals(checkoutOverviewPage.getExpectedPriceOfItemsInclTaxes(), checkoutOverviewPage.getActualTotalFromCheckoutPage(), "Price Validation Failed");

        ExtentManager.getTest().pass("Total Price of Cart Items match with Price displayed on Check out page");
    }

    @Test
    public void validateAddedItemsDetailsOnCheckoutTest() {
        loginPage.login();

        homePage.selectItemsOnPage(3).
                captureSelectedItemsDetailsFromHomePage(3).
                clickOnShoppingCart();

        yourCartPage.clickCheckoutButton();

        checkOutInfoPage.fillCheckoutInformationAndClickNext();

        checkoutOverviewPage.captureSelectedItemsFromCheckoutPage();

        Assert.assertEquals(CheckoutOverviewPage.inventoriesNameListCheckoutPage, HomePage.inventoryItemsNamesList);
        ExtentManager.getTest().pass("Details of added Items in the Cart match with Items ready for Checkout");
    }

}
