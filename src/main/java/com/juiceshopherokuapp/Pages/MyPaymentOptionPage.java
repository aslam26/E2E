package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class MyPaymentOptionPage extends BasePage{

    public MyPaymentOptionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css= "app-payment-method > div > div > mat-expansion-panel")
    WebElement addNewCard;

    @FindBy(css="div.mat-expansion-panel-body > div > mat-form-field.mat-form-field:nth-child(2) input")
    WebElement cardNumberField;


    @FindBy(css=".btn.nextButton")
    WebElement continueBtn;

    @FindBy(css="div.mat-expansion-panel-body > div > mat-form-field.mat-form-field:nth-child(3) select")
    WebElement expiryMonthField;


    @FindBy(css="div.mat-expansion-panel-body > div > mat-form-field.mat-form-field:nth-child(4) select")
    WebElement expiryYearField;


   @FindBy(css="div.mat-expansion-panel-body > div > mat-form-field.mat-form-field:nth-child(1) input")
    WebElement nameField;

   @FindBy(css="button#submitButton")
    WebElement submitBtn;

   @FindBy(xpath = "//span[@class='mat-simple-snack-bar-content']")
   WebElement cardDetailsAddedMessage;

   @FindBy(css="mat-cell > mat-radio-button")
   WebElement selectCardRadioBtn;

   public void setExpiryMonth(String month){
       Select select=new Select(expiryMonthField);
       select.selectByValue(month);
   }

   public void setExpiryYear(String year){
       Select select=new Select(expiryYearField);
       select.selectByValue(year);
   }

   public void addNewCardDetails(String name, String cardNumber,String month, String year ){
       addNewCard.click();
       nameField.sendKeys(name);
       cardNumberField.sendKeys(cardNumber);
       setExpiryMonth(month);
       setExpiryYear(year);
       submitBtn.click();
   }

   public void clickContinueBtn(){
       actions.pause(Duration.ofSeconds(2)).click(continueBtn).perform();
   }

   public void setSelectCardRadioBtn(){
       if(!selectCardRadioBtn.isSelected()){
           selectCardRadioBtn.click();
       }
   }

   public String addDetailsMessage(){
       return cardDetailsAddedMessage.getText();
   }
}
