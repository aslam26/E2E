package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Dictionary;

/**
 * Created By Aslam Mujawar on 20-11-2024
 */

public class DeliveryOptionsPage extends BasePage{

    public DeliveryOptionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//span[@class='mat-radio-outer-circle'])[3]")
    WebElement  standardDeliveryOptionThree;

    @FindBy(css=".btn.nextButton")
    WebElement continueBtn;

    @FindBy(css="mat-card > div.addressCont > div:nth-child(3)")
    WebElement deliveryAddress;

    @FindBy(css="mat-card > div.addressCont > div:nth-child(4)")
    WebElement deliveryCountry;

    @FindBy(css="mat-card > div.addressCont > div:nth-child(2)")
    WebElement deliveryName;

    public String getDeliveryAddress(){
      return deliveryAddress.getText();
    }

    public String getDeliveryCountry(){
        return deliveryCountry.getText();
    }

    public String getDeliveryName(){
        return deliveryName.getText();
    }

    public void selectDeliveryOption(){
        actions.pause(Duration.ofSeconds(2)).click(standardDeliveryOptionThree).build().perform();
    }

    public void clickContinueBtn(){
        continueBtn.click();
    }

}
