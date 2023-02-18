package testcases;

import functionalSteps.Operation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Verimi {
    Operation operation = new Operation();

    @BeforeTest
    public void setup() {
        operation.setupBrowser();
    }

    @Test
    public void testcases1() throws Exception {
        operation.accessUrl();
    }

    @Test
    public void testcases2() throws Exception {
        operation.loginTo_Verimi();
    }

    @Test
    public void testcases3() throws Exception {
        operation.get_WebElement_List();
    }
    @Test
    public void testcases4() throws Exception {
        operation.get_StreamList_and_printResult();
    }

    @AfterTest
    public void tearDown() {
        operation.tearDown();
    }
}