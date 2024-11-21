package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AddNewAddressPage extends BasePage{

    public AddNewAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='Add New Address']")
    WebElement addNewAddressButton;

    //@FindBy(id = "address-form")
    // WebElement addressForm;

    // Locator for the address field inside the form
    @FindBy(css = "#address-form .mat-form-field:nth-child(1) input")
     WebElement countryTextField;


    @FindBy(css=".mat-form-field:nth-child(2) input")
    WebElement nameTextField;

    @FindBy(css=".mat-form-field:nth-child(3) input")
    WebElement mobileTextField;

    @FindBy(css=".mat-form-field:nth-child(4) input")
    WebElement zipTextfield;

    @FindBy(css=".mat-form-field:nth-child(5) textarea")
    WebElement addressTextField;

    @FindBy(css=".mat-form-field:nth-child(6) input")
    WebElement cityTextField;

    @FindBy(css=".mat-form-field:nth-child(7) input")
    WebElement stateTextField;

    @FindBy(css="button#submitButton")
    WebElement submitBtn;

    @FindBy(css="h1[class='ng-star-inserted']")
    WebElement selectAddressTitle;


    public void clickAddNewAddress(){
        this.actions.pause(Duration.ofSeconds(2)).click(addNewAddressButton).perform();
    }

    public void setAddNewAddress(String country, String name, String mobile, String zipcode, String address, String city, String state){
        countryTextField.sendKeys(country);
        nameTextField.sendKeys(name);
        mobileTextField.sendKeys(String.valueOf(mobile));
        zipTextfield.sendKeys(String.valueOf(zipcode));
        addressTextField.sendKeys(address);
        cityTextField.sendKeys(city);
        stateTextField.sendKeys(state);
    }

    public void clickSubmitButton(){
        actions.pause(Duration.ofSeconds(2)).click(submitBtn).build().perform();
    }

    public String getTitleSelectAddress(){
        return wait.until(ExpectedConditions.visibilityOf(selectAddressTitle)).getText();
    }
    }
