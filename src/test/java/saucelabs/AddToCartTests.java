package saucelabs;

import org.testng.Assert;
import org.testng.annotations.*;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;
import saucelabs.pages.HomePage;
import saucelabs.pages.LoginPage;
import saucelabs.pages.YourCartPage;

public class AddToCartTests extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    YourCartPage yourCartPage;

    @BeforeClass
    public void testSetup() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        yourCartPage = new YourCartPage();
    }


    @Test
    public void validateAddToCartWithBudgetLimit30() {
        loginPage.login();
        ExtentManager.getTest().info("Going to Select items on page with Limit - $30");
        int total = homePage.selectItemsOnPage(30f);
        Assert.assertTrue(homePage.validateCartItems(total), "Cart Items don't match with Total items added");
        yourCartPage.removeAllItemsFromCart();
        ExtentManager.getTest().pass("Total Items in the cart match with items added on home page");
        ExtentManager.getTest().pass("Total Items in the cart match with items added on home page");

    }

    @Test
    public void validateAddToCartTest() {
        loginPage.login();

        // Taking 3 items as per the requirement
        homePage.selectItemsOnPage(3);

        Assert.assertTrue(homePage.validateCartItems(3), "Cart Items don't match with Total items added");
        yourCartPage.removeAllItemsFromCart();

        ExtentManager.getTest().pass("Total Items in the cart match with items added on home page");

    }

    @Test
    public void validateAddToCartWithRemoveItemsTest() {
        loginPage.login();

        homePage.selectItemsOnPage(3)
                .clickOnShoppingCart();

        yourCartPage.removeAllItemsFromCart()
                .clickContinueShoppingButton();

        homePage.selectItemsOnPage(3);
        Assert.assertTrue(homePage.validateCartItems(3), "Cart Items don't match with Total items added");
        yourCartPage.removeAllItemsFromCart();

        ExtentManager.getTest().pass("Total Items in the cart match with items added on home page");

    }

}
