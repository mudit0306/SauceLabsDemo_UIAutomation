package saucelabs;

import org.junit.jupiter.api.*;
import saucelabs.commons.CommonUtils;
import saucelabs.base.TestBase;
import saucelabs.pages.*;

import java.io.IOException;

public class CheckoutValidationTests extends TestBase {

    LoginPage loginPage ;
    HomePage homePage;
    YourCartPage yourCartPage;
    CheckOutInfoPage checkOutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage ;

    @BeforeEach
    public void setup(TestInfo testInfo) throws IOException {
        initializeDriver();
        loginPage= new LoginPage();
        homePage = new HomePage();
        yourCartPage = new YourCartPage();
        checkOutInfoPage= new CheckOutInfoPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        setupExtentReport(testInfo.getDisplayName());
    }

    @Test
    public void validateTotalPriceOfItemsOnCheckoutTest(){
        loginPage.login();

        homePage.selectItemsOnPage(3).clickOnShoppingCart();

        yourCartPage.clickCheckoutButton();

        checkOutInfoPage.fillCheckoutInformationAndClickNext();

        Assertions.assertEquals(checkoutOverviewPage.getExpectedPriceOfItemsInclTaxes(), checkoutOverviewPage.getActualTotalFromCheckoutPage());
        addPassLog("Total Price of Cart Items match with Price displayed on Check out page");


    }

    @Test
    public void validateAddedItemsDetailsOnCheckoutTest(){
        loginPage.login();

        homePage.selectItemsOnPage(3).
                captureSelectedItemsDetailsFromHomePage(3).
                clickOnShoppingCart();

        yourCartPage.clickCheckoutButton();

        checkOutInfoPage.fillCheckoutInformationAndClickNext();

        checkoutOverviewPage.captureSelectedItemsFromCheckoutPage();

        Assertions.assertEquals(CheckoutOverviewPage.inventoriesNameListCheckoutPage, HomePage.inventoryItemsNamesList);
        addPassLog("Details of added Items in the Cart match with Items ready for Checkout");
    }

    @AfterEach
    public void tearDown() throws IOException {
        CommonUtils.takeScreenShot(driver); ;
        flushExtentReport();
        driver.close();
    }
}
