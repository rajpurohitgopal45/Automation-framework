package Utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class WebDriver_Adapter extends Base {

    public void waitVisibilityOfElements(String strElement) throws Exception {
        try {
            By byElement = Page_Object_Reader.getElement(strElement);
            new WebDriverWait(driver, 60).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElement));
        } catch (Exception e) {
            throw new Exception("Exception occurred while visibility of all elements : " + e.getMessage());
        }
    }

    public void click(String strElement) throws Exception {
        try {
            By byElement = Page_Object_Reader.getElement(strElement);
            new WebDriverWait(driver, 100).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(byElement));
            driver.findElement(byElement).click();
            logger.info("clicked on element : "+strElement);
        } catch (Exception e) {
            throw new Exception("Exception occurred while clicking on element : " + e.getMessage());
        }
    }

    public void type(String strElement, String data) throws Exception {
        try {
            By byElement = Page_Object_Reader.getElement(strElement);
            new WebDriverWait(driver, 100).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(byElement));
            driver.findElement(byElement).sendKeys(data);
            logger.info("data entered in element : "+strElement);
        } catch (Exception e) {
            throw new Exception("Exception occurred while typing on element : " + e.getMessage());
        }
    }

    public List<WebElement> getWebElements(String strElement) throws Exception {
        try {
            By byElement = Page_Object_Reader.getElement(strElement);
            List<WebElement> elementList = driver.findElements(byElement);
            logger.info("get list of all webElements of : "+strElement);
            return elementList;
        } catch (Exception e) {
            throw new Exception("Exception occurred while getting list of elements : " + e.getMessage());
        }
    }

    public List<WebElement> getStreamList(List<WebElement> elements, String data) throws Exception {
        try {
            List<WebElement> streamList = elements.stream()
                    .filter(x -> x.getText().contains(data))
                    .collect(Collectors.toList());
            logger.info("get stream list of words which containing text : "+data);
            return streamList;
        } catch (Exception e) {
            throw new Exception("Exception occurred while getting Stream of elements : " + e.getMessage());
        }
    }

    public String getText(String strElement) throws Exception {
        try {
            By byElement = Page_Object_Reader.getElement(strElement);
            String actualText=driver.findElement(byElement).getText();
            return actualText;
        } catch (Exception e) {
            throw new Exception("Exception occurred while getting text of elements : " + e.getMessage());
        }
    }
}