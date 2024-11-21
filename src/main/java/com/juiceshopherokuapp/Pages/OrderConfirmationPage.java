package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderConfirmationPage extends BasePage{
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="div > mat-card:nth-child(1) > div > div")
    WebElement orderConfirmationMessage;

    @FindBy(css="div.confirmation")
    WebElement orderDeliveryMessage;

    @FindBy(css="h1.confirmation")
    WebElement thanksMessage;

    public String getOrderConfirmationMessage(){
        wait.until(ExpectedConditions.visibilityOf(thanksMessage));
        return orderConfirmationMessage.getText();
    }

    public String getOrderDeliveryMessage(){
        return orderDeliveryMessage.getText();
    }

    public String getThanksMessage(){
        return thanksMessage.getText();
    }


}
