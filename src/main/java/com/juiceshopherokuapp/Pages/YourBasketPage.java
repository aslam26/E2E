package com.juiceshopherokuapp.Pages;

import lombok.experimental.FieldNameConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YourBasketPage extends BasePage {
    public YourBasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
    WebElement yourBasketBtn;

    @FindBy(css="mat-table > mat-row:nth-child(2) > .mat-column-product")
    WebElement getAppleJuiceText;

    @FindBy(css="mat-table > mat-row:nth-child(2) > .mat-column-price")
    WebElement getAppleJuicePrice;

    @FindBy(css="mat-table > mat-row:nth-child(2) > .mat-column-quantity")
    WebElement getAppleJuiceQty;

    @FindBy(css="mat-table > mat-row:nth-child(3) > .mat-column-product")
    WebElement getBananaJuiceText;

    @FindBy(css = "mat-table > mat-row:nth-child(3) > .mat-column-price")
    WebElement getBananaJuicePrice;

    @FindBy(css="mat-table > mat-row:nth-child(3) > .mat-column-quantity")
    WebElement getBananaJuiceQty;

    @FindBy(css="button#checkoutButton")
    WebElement checkOutBtn;

    @FindBy(xpath="//div[@class='ng-star-inserted']")
    WebElement getTotalPrice;

    @FindBy(xpath = "//h1[@class='ng-star-inserted']")
    WebElement selectAnAddressHeader;




    public void clickYourBasket(){
        actions.pause(2).click(yourBasketBtn).perform();
    }

    public String getAppleJuiceTextFromOrders(){
        wait.until(ExpectedConditions.visibilityOf(getAppleJuicePrice));
        return getAppleJuiceText.getText();
    }

    public double getAppleJuicePriceFromOrders(){
        try {
            String price = getAppleJuicePrice.getText();
            String cleanPrice = price.replaceAll("[^a-zA-Z0-9.]", "");
            return Double.parseDouble(cleanPrice);
        }catch (Exception e){
            System.out.println("Error: Invalid format");
            return 0.0;
        }
    }

    public int getAppleJuiceQuantity(){
        return Integer.parseInt(getAppleJuiceQty.getText());
    }

    public String getBananaJuiceTextFromOrders(){
        wait.until(ExpectedConditions.visibilityOf(getBananaJuiceText));
        return getBananaJuiceText.getText();
    }

    public double getBananaPriceFromOrders(){
        try {
            String price = getBananaJuicePrice.getText();
            String cleanPrice = price.replaceAll("[^a-zA-Z0-9.]", "");
            return Double.parseDouble(cleanPrice);
        }catch (Exception e){
            System.out.println("Error: banana invalid format");
            return 0.0;
        }
    }

    public int getBananaJuiceQtyFromOrders(){
        return Integer.parseInt(getBananaJuiceQty.getText());
    }

    public double getTotalPriceFromOrders(){
        try {
            wait.until(ExpectedConditions.visibilityOf(getTotalPrice));
            String price = getTotalPrice.getText();
            String cleanPrice = price.replaceAll("[^\\d.]", "");
            return Double.parseDouble(cleanPrice);
        }catch (Exception e){
            System.out.println("Error: total invalid format");
            return 0.0;
        }
    }


    public void clickCheckOut(){
        actions.pause(5).click(checkOutBtn).build().perform();
    }

    public String getSelectAnAddress(){
        wait.until(ExpectedConditions.textToBePresentInElement(selectAnAddressHeader,"Select an address"));
        return selectAnAddressHeader.getText();
    }







}
