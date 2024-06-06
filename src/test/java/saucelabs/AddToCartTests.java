package saucelabs;

import org.junit.jupiter.api.*;
import saucelabs.commons.CommonUtils;
import saucelabs.base.TestBase;
import saucelabs.pages.*;

import java.io.IOException;

public class AddToCartTests extends TestBase {
    LoginPage loginPage ;
    HomePage homePage;
    YourCartPage yourCartPage;

    @BeforeEach
    public void setup(TestInfo testInfo) throws IOException {
        initializeDriver();
        loginPage= new LoginPage();
        homePage = new HomePage();
        yourCartPage = new YourCartPage();
        setupExtentReport(testInfo.getDisplayName());
    }

    @Test
    public void validateAddToCartTest(){
        loginPage.login();

        // Taking 3 items as per the requirement
        homePage.selectItemsOnPage(3);

        Assertions.assertTrue(homePage.validateCartItems(3),"Cart Items don't match with Total items added");

        addPassLog("Total Items in the cart match with items added on home page");

    }

    @Test
    public void validateAddToCartWithRemoveItemsTest(){
        loginPage.login();

        homePage.selectItemsOnPage(3)
                .clickOnShoppingCart();

        yourCartPage.removeAllItemsFromCart()
                .clickContinueShoppingButton();

        homePage.selectItemsOnPage(3);
        Assertions.assertTrue(homePage.validateCartItems(3),"Cart Items don't match with Total items added");

        addPassLog("Total Items in the cart match with items added on home page");

    }

    @AfterEach
    public void tearDown() throws IOException {
        CommonUtils.takeScreenShot(driver); ;
        flushExtentReport();
        driver.close();
    }
}
