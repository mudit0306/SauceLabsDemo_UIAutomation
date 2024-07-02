package saucelabs.ExtentManagerUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashSet;
import java.util.Set;

public class ExtentTestNGITestListener implements ITestListener {
    private Set<String> testClasses = new HashSet<>();


    private String getClassName(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = getClassName(result);
        testClasses.add(className);
        ExtentManager.startTest(className, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip(result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        // Code to run before the start of the suite
    }

    @Override
    public void onFinish(ITestContext context) {

        for (String className : testClasses) {
            ExtentManager.endTest(className);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }
}
