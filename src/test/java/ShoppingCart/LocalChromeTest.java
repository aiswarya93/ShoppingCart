package ShoppingCart;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Utilities;


/*Test class to run Scenario 1 and Scenario 2 on chrome in an Windows in Local machine*/
public class LocalChromeTest extends Utilities {
    @BeforeTest
    public void launchBrowser() {
            browser("Chrome_Local");
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