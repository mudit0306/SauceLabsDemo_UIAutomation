package saucelabs.ExtentManagerUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;
    private static Map<String, ExtentReports> extentMap = new HashMap<>();
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static synchronized ExtentReports getReporter(String className){
        if (!extentMap.containsKey(className)) {

            String reportPath = System.getProperty("user.dir") + "/target/ExtentReports/" + className + "_ExtentReport.html";

            htmlReporter = new ExtentSparkReporter(reportPath);

            final File CONF = new File("extentconfig.json");
            try {
                htmlReporter.loadJSONConfig(CONF);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Tester", "Mudit Gaur");

            extentMap.put(className, extent);

        }
        return extentMap.get(className);
    }

    public static synchronized ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static synchronized  void startTest(String className, String testName) {
        ExtentTest test = getReporter(className).createTest(testName);
        extentTestThreadLocal.set(test);
    }

    public static synchronized void endTest(String className) {
            getReporter(className).flush();
            extentTestThreadLocal.remove();
    }
}
