package functionalSteps;

import Utility.Base;
import Utility.WebDriver_Adapter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class Operation extends Base {
    WebDriver_Adapter objWebDriver_adapter = new WebDriver_Adapter();
    List<WebElement> elements, result;

    public void setupBrowser() {
        setup();
    }
    public void accessUrl() throws IOException {
        Base.url();
        logInfo("Url launched : " + propertyFile("TestUrl"));
    }

    public void loginTo_Verimi() throws Exception {
        objWebDriver_adapter.click("deLang");
        objWebDriver_adapter.waitVisibilityOfElements("emailHeading");

        objWebDriver_adapter.type("txtEmail", propertyFile("email"));
        objWebDriver_adapter.type("txtPass", propertyFile("password"));
        objWebDriver_adapter.click("btnLogin");
        objWebDriver_adapter.waitVisibilityOfElements("subTitle");

        String expectedText=propertyFile("expectedText");
        Assert.assertEquals(objWebDriver_adapter.getText("myProfile")
                ,expectedText);
        logger.info("----------user directed to main page--------------");
        logInfo("--------------Login Successfully---------------------");
    }

    public void get_WebElement_List() throws Exception {
        elements = objWebDriver_adapter.getWebElements("tagNameP");
        logInfo("--------------List successfully created of webElements by tagName p -----------------");
    }

    public void get_StreamList_and_printResult() throws Exception {
        result = objWebDriver_adapter.getStreamList(elements, propertyFile("getResultByText"));

        logInfo("--------------Refer below result lines containing word "+propertyFile("getResultByText")+"---------------------");
        result.forEach((n) -> logInfo(n.getText()));
        result.forEach((n)-> logger.info(n.getText()));
    }

    public void tearDown() {
        Base.close();
    }
}