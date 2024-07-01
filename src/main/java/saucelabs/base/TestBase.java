package saucelabs.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import saucelabs.ExtentManagerUtils.ExtentManager;
import saucelabs.commons.CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public static Properties prop;

    // ThreadLocal is used for thread safety, we can manage WebDriver instances effectively in parallel test execution.
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/saucelabs/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void setup() {
        driver.set(new ChromeDriver());
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public static void captureAndAddScreenshot() throws IOException {
        String screenshotPath = CommonUtils.takeScreenShot(getDriver());
        ExtentTest test = ExtentManager.getTest();
        test.log(Status.INFO, "Screenshot:",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    @AfterClass
    public static void tearDownDriver() {
        getDriver().close();
    }

}
