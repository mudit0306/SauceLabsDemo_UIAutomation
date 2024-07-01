package saucelabs;

import org.testng.Assert;
import org.testng.annotations.*;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;
import saucelabs.pages.*;


public class CheckoutCompleteE2ETests extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    YourCartPage yourCartPage;
    CheckOutInfoPage checkOutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;


    @BeforeClass
    public void testSetup() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        yourCartPage = new YourCartPage();
        checkOutInfoPage = new CheckOutInfoPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();
    }


    @Test
    public void successfulCheckoutTest() {
        loginPage.login();

        homePage.selectItemsOnPage(3).clickOnShoppingCart();

        yourCartPage.clickCheckoutButton();

        checkOutInfoPage.fillCheckoutInformationAndClickNext();

        checkoutOverviewPage.finishCheckout();

        //Validate Title
        Assert.assertEquals(checkoutCompletePage.getCheckoutCompletePageTitle(), prop.getProperty("checkout_complete_title_message"));

        //Validate Checkout Complete Message
        Assert.assertTrue(checkoutCompletePage.getCheckoutcompleteMessage().contains(prop.getProperty("confirmation_message")));

        // Validate home button is displayed and enabled
        Assert.assertTrue(checkoutCompletePage.getBackHomeButton().isEnabled());

        ExtentManager.getTest().pass("Checkout Completed Successfully with expected Confirmation Messages displayed");
    }

}
