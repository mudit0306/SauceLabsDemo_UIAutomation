package saucelabs.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public static Properties prop;
    public static WebDriver driver;
    static ExtentTest test;
    static ExtentReports extent;

    String currentDir = System.getProperty("user.dir");

    public static void initializeDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //More cookies can be set as and when we go forward in our testing

        driver.get(prop.getProperty("url"));

    }


    public TestBase(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/saucelabs/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupExtentReport(String testName) throws IOException {

        extent = new ExtentReports();
        String folderPath = currentDir+"/target/ExtentReports";

        File folder = new File(folderPath);
        if(!folder.exists())
             folder.mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(folderPath+"/"+testName+"_ExtentTestReport.html");

        final File CONF = new File("extentconfig.json");
        spark.loadJSONConfig(CONF);
        extent.attachReporter(spark);
        test = extent.createTest("Select and Checkout Test").assignAuthor("Mudit gaur");

    }

    public void addInfoLog(String logMessage){
        test.log(Status.INFO,logMessage);

    }

    public void addPassLog(String logMessage){
        test.log(Status.PASS,logMessage);

    }
    public void flushExtentReport(){
        test.addScreenCaptureFromPath(currentDir + "/screenshots/test.png");
        extent.flush();
    }

}
