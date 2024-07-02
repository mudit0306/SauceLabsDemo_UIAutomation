package saucelabs.pages;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.base.TestBase;

public class LoginPage extends TestBase {

    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginButton;


    public LoginPage(){
        PageFactory.initElements(driver.get(), this);
    }
    public void login(){

        ExtentManager.getTest().info("Opening url - "+prop.getProperty("url"));
        driver.get().get(prop.getProperty("url"));

        username.sendKeys(prop.getProperty("username"));
        password.sendKeys(prop.getProperty("password"));
        loginButton.click();

        ExtentManager.getTest().pass(MarkupHelper.createLabel("Login completed successfully", ExtentColor.GREEN));
    }

}
