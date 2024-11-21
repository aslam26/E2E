package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SelectAddressPage extends BasePage {

    public SelectAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="(//span[@class='mat-radio-inner-circle'])[1]")
    WebElement radioBtn;

    @FindBy(css=".btn.btn-next")
    WebElement continueBtn;

    @FindBy(css="mat-table > mat-row:nth-child(2) > .cdk-column-Name")
    WebElement getNameInSelectAddress;


    public void selectRadioBtn() {
        try {
            // Check if the radio button is not already selected
            if (!radioBtn.isSelected()) {
                radioBtn.click();
            }
        } catch (Exception e) {
            // Handle any unexpected issues and log the error
            System.err.println("Failed to select the radio button: " + e.getMessage());
            throw e; // Re-throw the exception if needed for the test to fail
        }
    }
    public void clickContinueBtn(){
        actions.pause(Duration.ofSeconds(2)).click(continueBtn).perform();
    }




}
