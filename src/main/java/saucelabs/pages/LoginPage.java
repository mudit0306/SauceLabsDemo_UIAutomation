package saucelabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import saucelabs.base.TestBase;

public class LoginPage extends TestBase {

    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginButton;


    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
    public void login(){
        username.sendKeys(prop.getProperty("username"));
        password.sendKeys(prop.getProperty("password"));
        loginButton.click();

        addPassLog("Login completed successfully ");

    }

}
