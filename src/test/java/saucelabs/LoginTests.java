package saucelabs;


import org.testng.Assert;
import org.testng.annotations.*;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;
import saucelabs.pages.LoginPage;

public class LoginTests extends TestBase {
    LoginPage loginPage;

    @BeforeClass
    public void testSetup() {
        loginPage = new LoginPage();
    }

    @Test
    public void SuccessfulLoginTest() {
        loginPage.login();
        Assert.assertEquals(prop.getProperty("home_page_title"), driver.get().getTitle());
        ExtentManager.getTest().pass("User is able to Login and see the Home page on Sauce Labs website");
    }

}
