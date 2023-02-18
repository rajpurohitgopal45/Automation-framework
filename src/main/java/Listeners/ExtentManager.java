package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports createInstance(String filename) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);

        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(filename);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(filename);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

}