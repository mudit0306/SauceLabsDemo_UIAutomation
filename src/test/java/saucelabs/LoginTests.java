package saucelabs;

import org.junit.jupiter.api.*;
import saucelabs.commons.CommonUtils;
import saucelabs.base.TestBase;
import saucelabs.pages.*;

import java.io.IOException;

public class LoginTests extends TestBase {
    LoginPage loginPage ;

    @BeforeEach
    public void setup(TestInfo testInfo) throws IOException {
        initializeDriver();
        loginPage= new LoginPage();
        setupExtentReport(testInfo.getDisplayName());
    }

    @Test
    public void SuccessfulLoginTest(){
        loginPage.login();

        Assertions.assertEquals(prop.getProperty("home_page_title"), driver.getTitle());

        addPassLog("User is able to Login and see the Home page on Sauce Labs website");
    }

    @AfterEach
    public void tearDown() throws IOException {
        CommonUtils.takeScreenShot(driver); ;
        flushExtentReport();
        driver.close();
    }
}
