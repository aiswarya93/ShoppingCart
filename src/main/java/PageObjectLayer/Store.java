package PageObjectLayer;

import org.openqa.selenium.By;

public class Store {

    public By storeLink = By.linkText("Store");
    public By CartLink = By.partialLinkText("Cart");

    public By productPrice(String productName) {
        By productPrice = By.xpath("//p[contains(text(),'"+productName+"')]/following::h3");
        return productPrice;
    }


    public By addToCart(String productName) {
        By addToCart = By.xpath("//p[contains(text(),'" + productName + "')]/following-sibling::div/button");
        return addToCart;
    }


}
