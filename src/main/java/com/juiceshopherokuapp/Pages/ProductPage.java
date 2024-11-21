package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.sound.midi.Soundbank;
import java.time.Duration;

public class ProductPage extends BasePage{


    public ProductPage(WebDriver driver) {
        super(driver);


    }

    @FindBy(xpath = "(//button[@aria-label='Add to Basket'])[1]")
    WebElement appleJuiceAddToCartBtn;

    @FindBy(xpath = "(//button[@aria-label='Add to Basket'])[3]")
    WebElement bananaJuiceAddToCartBtn;

    @FindBy(xpath = "(//div[@class='item-name'])[1]")
    WebElement getAppleJuiceText;


    @FindBy(xpath = "(//div/span[@class='ng-star-inserted'])[1]")
    WebElement getAppleJuicePrice;

    @FindBy(xpath = "(//div[@class='item-name'])[3]")
    WebElement getBananaJuiceText;

    @FindBy(xpath = "(//span[@class='ng-star-inserted'])[3]")
    WebElement getBananaJuicePrice;

    @FindBy(xpath = "//span[@class='fa-layers-counter fa-layers-top-right fa-3x warn-notification']")
    WebElement badgeCount;

    @FindBy(xpath = "//span[@class='mat-simple-snack-bar-content']")
    WebElement juiceAddedMessage;

    @FindBy(css=".cdk-overlay-backdrop.cdk-overlay-transparent-backdrop.cdk-overlay-backdrop-showing")
    WebElement overlay;


    public void addAppleJuiceToCart(){
        overlay.click();
        actions.pause(Duration.ofSeconds(10)).click(appleJuiceAddToCartBtn).perform();

    }
    public void addBananaJuiceAddToCart(){
        actions.pause(Duration.ofSeconds(10)).click(bananaJuiceAddToCartBtn).build().perform();

    }
    public String getAppleJuiceTextFromProduct(){
       return   getAppleJuiceText.getText();
    }
    public double getAppleJuicePriceFromProduct(){
        try {
            String price = getAppleJuicePrice.getText();
            String cleanPrice = price.replaceAll("[^a-zA-Z0-9.]", "");
            return Double.parseDouble(cleanPrice);
        }catch (Exception e){
            System.out.println("Error, Invalid format");
            return 0.0;
        }
    }

    public String getBananaJuiceTextFromProduct(){
        return getBananaJuiceText.getText();
    }

    public double getBananaJuicePriceFromProduct(){
        try {
            String price = getBananaJuicePrice.getText();
            String cleanPrice = price.replaceAll("[^a-zA-Z0-9.]", "");
            return Double.parseDouble(cleanPrice);
        }catch (Exception e){
            System.out.println("Error: Invalid format");
            return 0.0;
        }
    }

    public String getBasketCount(int expectedCount){
        String expectedbadgeCount=String.valueOf(expectedCount);
        this.wait.until(ExpectedConditions.textToBePresentInElement(badgeCount,expectedbadgeCount));
        return badgeCount.getText();
    }

    public String getSuccessMessage(){
         this.wait.until(ExpectedConditions.visibilityOf(juiceAddedMessage));
        return juiceAddedMessage.getText();
    }


}
