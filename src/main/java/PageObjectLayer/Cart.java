package PageObjectLayer;

import org.openqa.selenium.By;

public class Cart {


    public By quantity = By.xpath("//h1[contains(text(),'Cart')]/following::h5/following::p[contains(text(),'Qty')]");
    public By item = By.xpath("//p[contains(text(),'Total Items')]/following-sibling::h4");
    public By price = By.xpath("//p[contains(text(),'Total Payment')]/following-sibling::h3");
    public By clearButton = By.xpath("//button[contains(text(),'CLEAR')]");
    public By cartEmptyMsg = By.xpath("//div[contains(text(),'Your cart is empty')]");
    public By checkoutButton = By.xpath("//button[contains(text(),'CHECKOUT')]");
    public By checkoutSuccessMsg = By.xpath("//p[contains(text(),'Checkout successfull')]");

    public By productNameinCart(String productName) {
         By productNameinCart = By.xpath("//h5[contains(text(),'"+productName+"')]");
        return productNameinCart;
    }

    public By deleteButton(String productName) {
         By deleteButton = By.xpath("//h5[contains(text(),'"+productName+"')]/following::button[2]");
        return deleteButton;
    }

    public By addButton(String productName) {
         By addButton = By.xpath("//h5[contains(text(),'"+productName+"')]/following::button[1]");
        return addButton;
    }




}
