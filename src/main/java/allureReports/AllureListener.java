package allureReports;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    /*@Attachment
    public byte[] saveFailureScreenShot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }*/

    /*@Attachment(value = "{0}",type = "text/plain")
    public static void saveTextLog(String message){
        System.out.println(message);
    }*/

    @Attachment(value = "{0}",type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }

    @Override
    public void onStart(ITestContext iTestContext){
        System.out.println("Starting on "+iTestContext.getName());
        iTestContext.setAttribute("WebDriver",BaseClass.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext){
        System.out.println("Finishing on "+iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        System.out.println("Now on method "+ getTestMethodName(iTestResult)+" start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        System.out.println("Now on success method "+ getTestMethodName(iTestResult)+" succeed");
    }


    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("Now on failure method "+ getTestMethodName(iTestResult)+" failed");
        Object testClass=iTestResult.getInstance();
        WebDriver driver=BaseClass.getDriver();
        //Allure Screenshot and SaveTestLog
        if (driver != null){
          //  if (driver instanceof WebDriver){
            System.out.println("Screenshot captured for test case: "+getTestMethodName(iTestResult));
            saveFailureScreenShot(driver);
        }
        saveTextLog(getTestMethodName(iTestResult)+" failed and screenshot taken.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        System.out.println("Now on skipped method "+ getTestMethodName(iTestResult)+" skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult){
        System.out.println("Test failed but it is in defined success ratio "+ getTestMethodName(iTestResult));
    }
}
