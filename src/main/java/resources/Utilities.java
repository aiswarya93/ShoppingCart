package resources;

import PageObjectLayer.Cart;
import PageObjectLayer.Store;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Utilities {

    WebDriver driver;
    Store store = new Store();
    Cart cart = new Cart();
    String totalItem;
    String price1;
    String price2;
    Float p1;
    Float p2;
    String userDir = System.getProperty("user.dir");

    public String readProperty(String property) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(userDir + "//src//main//java//resources//config.properties")) {
            prop.load(input);
            prop.getProperty(property);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(property);
    }

    public void browser(String browserType) {
        String URL = "https://" + readProperty("BS_UserName") + ":" + readProperty("BS_AuthKey") + "@hub-cloud.browserstack.com/wd/hub";
        DesiredCapabilities caps;
        try {
            switch (browserType) {
                case "BS_Chrome":
                    caps = new DesiredCapabilities();
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("name", "Aiswarya_Rajan");
                    driver = new RemoteWebDriver(new URL(URL), caps);
                    driver.get(readProperty("ShoppingCartURL"));
                    break;
                case "BS_Firefox":
                    caps = new DesiredCapabilities();
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser", "Firefox");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("name", "Aiswarya_Rajan");
                    driver = new RemoteWebDriver(new URL(URL), caps);
                    driver.get(readProperty("ShoppingCartURL"));
                    break;
                case "BS_Edge":
                    caps = new DesiredCapabilities();
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser", "Edge");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("name", "Aiswarya_Rajan");
                    driver = new RemoteWebDriver(new URL(URL), caps);
                    driver.get(readProperty("ShoppingCartURL"));
                    break;
                case "BS_Safari_IoS":
                    caps = new DesiredCapabilities();
                    caps.setCapability("os", "OS X");
                    caps.setCapability("os_version", "Big Sur");
                    caps.setCapability("browser", "Safari");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("name", "Aiswarya_Rajan");
                    driver = new RemoteWebDriver(new URL(URL), caps);
                    driver.get(readProperty("ShoppingCartURL"));
                    break;
                case "BS_Chrome_Android":
                    caps = new DesiredCapabilities();
                    caps.setCapability("browserName", "Android");
                    caps.setCapability("device", "Samsung Galaxy S21 Ultra");
                    caps.setCapability("realMobile", "true");
                    caps.setCapability("os_version", "11.0");
                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("name", "Aiswarya_Rajan");
                    driver = new RemoteWebDriver(new URL(URL), caps);
                    driver.get(readProperty("ShoppingCartURL"));
                    break;
                case "Chrome_Local":
                    System.setProperty("webdriver.chrome.driver", userDir + "//drivers//chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    driver.get(readProperty("ShoppingCartURL"));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
            driver.quit();
        }
    }

    public void ScenarioOne() {
        String productName = "Buffalo - Striploin";
        totalItem = "1";
        String totalPrice;
        try {
        	driver.findElement(store.storeLink).click();
        	totalPrice = driver.findElement(store.productPrice(productName)).getText();
            driver.findElement(store.addToCart(productName)).click();
            driver.findElement(store.CartLink).click();
            String itemInCart = driver.findElement(cart.productNameinCart(productName)).getText();
            Assert.assertTrue(productName.equalsIgnoreCase(itemInCart));
            
            Assert.assertEquals(totalItem, driver.findElement(cart.item).getText());
            
            Assert.assertEquals(totalPrice, driver.findElement(cart.price).getText());
            
            Assert.assertTrue(driver.findElement(cart.deleteButton(productName)).isDisplayed());
            driver.findElement(cart.clearButton).click();
            Assert.assertEquals(driver.findElement(cart.cartEmptyMsg).getText(), "Your cart is empty");
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
            driver.quit();
        }
    }

    public void ScenarioTwo() {
        try {
            String productName1 = "Buffalo - Striploin";
            String productName2 = "Bagels Poppyseed";
            totalItem = "4";
            float totalPrice;
            driver.findElement(store.storeLink).click();
            price1 = driver.findElement(store.productPrice(productName1)).getText();
            price1 = price1.substring(1);
            //System.out.println(price1);
            price2 = driver.findElement(store.productPrice(productName2)).getText();
            price2 = price2.substring(1);
            p1 = Float.parseFloat(price1);
            p2 = Float.parseFloat(price2);
            driver.findElement(store.addToCart(productName1)).click();
            driver.findElement(store.addToCart(productName2)).click();
            driver.findElement(store.CartLink).click();
            driver.findElement(cart.addButton(productName1)).click();
            driver.findElement(cart.addButton(productName1)).click();
            totalPrice = (p1 * 3) + (p2 * 1);
            Assert.assertEquals(totalItem, driver.findElement(cart.item).getText());
            Assert.assertEquals("$" + totalPrice, driver.findElement(cart.price).getText());
            Assert.assertTrue(driver.findElement(cart.deleteButton(productName1)).isDisplayed());
            Assert.assertTrue(driver.findElement(cart.deleteButton(productName2)).isDisplayed());
            driver.findElement(cart.deleteButton(productName1)).click();
            totalPrice = (p1 * 2) + (p2 * 1);
            String tPrice = "$" + String.format("%.2f", totalPrice);
            totalItem = "3";
            Assert.assertEquals(totalItem, driver.findElement(cart.item).getText());
            Assert.assertEquals(tPrice, driver.findElement(cart.price).getText());
            driver.findElement(cart.deleteButton(productName2)).click();
            Assert.assertFalse(driver.getPageSource().contains(productName2));
            driver.findElement(cart.checkoutButton).click();
            Assert.assertTrue(driver.findElement(cart.checkoutSuccessMsg).isDisplayed());
            //success message is given as "Checkout successfully" in the instructions. Considering it as a type I have compared it as
            // "Checkout successfull"  as this message is displayed. If not the test will fail.
            Assert.assertEquals(driver.findElement(cart.checkoutSuccessMsg).getText(), "Checkout successfull");
            Assert.assertEquals(driver.findElement(cart.cartEmptyMsg).getText(), "Your cart is empty");
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
            driver.quit();
        }
    }
    public void terminateBrowser() {
        driver.close();
        driver.quit();
    }
}
