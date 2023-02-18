package Listeners;

import java.io.IOException;
import java.util.Arrays;

import Utility.Base;
import com.aventstack.extentreports.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestListners implements ITestListener {
    static String fileName = "Test_Report.html";

    private static ExtentReports extent = ExtentManager
            .createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent
                .createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
        testReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(m);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testReport.get()
                .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                        + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");

        String errorUrl=Base.driver.getCurrentUrl();
        testReport.get().fail("<b>" + "<font color=" + "red>" +"error occurred in this url : "
                + "</font>" + "</b>"+errorUrl);

        try {
            String base64Screenshot = ((TakesScreenshot)Base.driver).getScreenshotAs(OutputType.BASE64);
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
            testReport.get().fail("<b>" + "<font color=" + "red>" +
                    "Screenshot of failure   : " + "</font>" + "</b>", mediaModel);
        } catch (IOException e) {
        }
        String failureLogg = "TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        testReport.get().log(Status.FAIL, m);
        Base.driver.close();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        testReport.get().skip(m);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}