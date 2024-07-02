package saucelabs.commons;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CommonUtils {
    static String currentDir = System.getProperty("user.dir");

    public static String takeScreenShot(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = currentDir+"/screenshots/" + System.currentTimeMillis() + ".png";

        FileUtils.copyFile(scrFile, new File(dest)) ;
        return dest;
    }
}
