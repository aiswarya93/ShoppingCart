package ShoppingCart;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Utilities;

/*Test class to run Scenario 1 and Scenario 2 on chrome in an Windows in browser stack*/
public class BrowserStackWindowsChromeTest extends Utilities {

    @BeforeTest
    public void launchBrowser() {
        browser("BS_Chrome");
    }
    @Test
    public void firstTestCase() {
        ScenarioOne(); }
    @Test
    public void secondTestCase() {
        ScenarioTwo(); }

    @AfterTest
    public void endTest() {
        terminateBrowser();
    }
}