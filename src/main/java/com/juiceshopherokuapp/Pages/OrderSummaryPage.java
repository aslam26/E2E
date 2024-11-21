package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class OrderSummaryPage extends BasePage{

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="mat-card > table > tr:nth-child(2) > td.mat-cell.price")
    WebElement getDeliveryAmount;

    @FindBy(css="mat-card > table > tr:nth-child(1) > td.mat-cell.price")
    WebElement getItemsPrice;


    @FindBy(css="mat-card > table > tr:nth-child(4) > td.mat-footer-cell.price")
    WebElement getTotalPrice;

    @FindBy(css="button#checkoutButton")
    WebElement placeOrderBtn;


    public double getDeliveryAmountFromOrderSummary(){
        String rawAmount=getDeliveryAmount.getText();
        String deliveryAmt=rawAmount.replaceAll("[^\\d.]", "");
        return Double.parseDouble(deliveryAmt);
    }

    public double getDeliveryItemPriceFromOrderSummary(String price){
        wait.until(ExpectedConditions.textToBePresentInElement(getItemsPrice,price));
        String rawPrice=getItemsPrice.getText();
        String itemPrice=rawPrice.replaceAll("[^\\d.]", "");
        return Double.parseDouble(itemPrice);
    }

    public double getTotalPriceFromOrderSummary(){
        String rawTotalPrice=getTotalPrice.getText();
        String totalPrice=rawTotalPrice.replaceAll("[^\\d.]", "");
        return Double.parseDouble(totalPrice);
    }


    public void placeOrderAndPay () {
        actions.pause(Duration.ofSeconds(3)).click(placeOrderBtn).build().perform();
    }




}
