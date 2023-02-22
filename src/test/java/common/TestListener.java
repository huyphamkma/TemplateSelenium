package common;

import helpers.CaptureHelpers;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Log;


public class TestListener extends BaseSetup implements ITestListener{

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext result) {
        System.out.println("Test start");
    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Test finished");

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Start: " + result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test pass: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println("Test fail: "+ result.getName());
        Log.error("Test fail: " + result.getName());
        try{
            CaptureHelpers.captureScreenshot(driver, result.getName());
        }catch (Exception e){
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }

        // Allure
        saveScreenshotPNG();
        saveTextLog(result.getName() + " failed and screenshot taken!");

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }
}
