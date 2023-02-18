package Utility;

import Listeners.TestListners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.util.concurrent.TimeUnit;

public class Base {
    public static WebDriver driver;
    public static Logger logger= LogManager.getLogger();

    public void setup() {

        logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        logger.info("Test Has Started");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Browser instance created");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void url() throws IOException {
        driver.get(propertyFile("TestUrl"));
        logger.info("Launched url"+propertyFile("TestUrl"));
    }

    public static void logInfo(String info){
        try {
            TestListners.testReport.get().info(info);
        } catch (Throwable t) {
            System.out.println(t);
        }
    }

    public static String propertyFile(String key) throws IOException {
        Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\resources\\inputData.properties");
        obj.load(objfile);
        String data=obj.getProperty(key);
        return data;
    }

    public static void close(){
        driver.close();
        logger.info("Browser closed");
        logger.info("Test Has Finished");
        logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
    }
}