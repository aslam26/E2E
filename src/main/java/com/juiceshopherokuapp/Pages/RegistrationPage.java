package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class RegistrationPage extends BasePage{

    private Actions actions;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        actions=new Actions(driver);
    }


    @FindBy(css="[href='#/register']")
    WebElement notYetACustomerLink;

    @FindBy(css = "input#emailControl")
    WebElement emailTextField;

    @FindBy(css="input#passwordControl")
    WebElement passwordTextField;

    @FindBy(css="input#repeatPasswordControl")
    WebElement repeatPasswordTextField;

    @FindBy(css = "[name='securityQuestion']")
    WebElement securityQDropdown;

    @FindBy(css="input#securityAnswerControl")
    WebElement securityAnswerTextField;

    @FindBy(css="button#registerButton")
    WebElement registerBtn;

    @FindBy(xpath = "//span[@class='mat-simple-snack-bar-content']")
    WebElement successMsg;

    private void setSecurityQDropdown(String securityQuestion){
        actions.pause(Duration.ofSeconds(2)).click(securityQDropdown).perform();
        String xpathSec= String.format("//mat-option/span[contains(text(),'%s')]", securityQuestion);
        WebElement favPetOption=driver.findElement(By.xpath(xpathSec));
        actions.pause(Duration.ofSeconds(2)).click(favPetOption).build().perform();

    }

    public void registerUser(String email, String password, String securityQuestions, String securityAnswer){
        actions.pause(Duration.ofSeconds(2)).click(notYetACustomerLink).build().perform();
        emailTextField.sendKeys(email);
        passwordTextField.sendKeys(password);
        repeatPasswordTextField.sendKeys(password);
        setSecurityQDropdown(securityQuestions);
        securityAnswerTextField.sendKeys(securityAnswer);
        registerBtn.click();
    }

    public String getSuccessMessage(){
        successMsg.isDisplayed();
        String successMessage=successMsg.getText();
        return successMessage;
    }


}
