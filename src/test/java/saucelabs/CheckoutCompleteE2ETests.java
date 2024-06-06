package saucelabs;

import org.junit.jupiter.api.*;
import saucelabs.commons.CommonUtils;
import saucelabs.base.TestBase;
import saucelabs.pages.*;

import java.io.IOException;


public class CheckoutCompleteE2ETests extends TestBase {

    LoginPage loginPage ;
    HomePage homePage;
    YourCartPage yourCartPage;
    CheckOutInfoPage checkOutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage ;
    CheckoutCompletePage checkoutCompletePage;


    @BeforeEach
    public void setup(TestInfo testInfo) throws IOException {
        initializeDriver();
        loginPage= new LoginPage();
        homePage = new HomePage();
        yourCartPage = new YourCartPage();
        checkOutInfoPage= new CheckOutInfoPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();
        setupExtentReport(testInfo.getDisplayName());
    }


    @Test
    public void successfulCheckoutTest(){
        loginPage.login();

        homePage.selectItemsOnPage(3).clickOnShoppingCart();

        yourCartPage.clickCheckoutButton();

        checkOutInfoPage.fillCheckoutInformationAndClickNext();

        checkoutOverviewPage.finishCheckout();

        //Validate Title
        Assertions.assertEquals(checkoutCompletePage.getCheckoutCompletePageTitle(),prop.getProperty("checkout_complete_title_message"));

        //Validate Checkout Complete Message
        Assertions.assertTrue(checkoutCompletePage.getCheckoutcompleteMessage().contains(prop.getProperty("confirmation_message")));

        // Validate home button is displayed and enabled
        Assertions.assertTrue(checkoutCompletePage.getBackHomeButton().isEnabled());

        addPassLog("Checkout Completed Successfully with expected Confirmation Messages displayed");

    }

    @AfterEach
    public void tearDown() throws IOException {
        CommonUtils.takeScreenShot(driver); ;
        flushExtentReport();
        driver.close();
    }

}
