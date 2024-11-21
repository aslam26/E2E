package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="input#email")
    WebElement emailTextfield;

    @FindBy(css="input#password")
    WebElement passwordTextfield;

    @FindBy(css="button#loginButton")
    WebElement loginBtn;

    @FindBy(css = "button#navbarLogoutButton")
    public WebElement logoutBtn;

    public void performLogin(String email, String password){
        wait.until(ExpectedConditions.visibilityOf(emailTextfield));
        emailTextfield.sendKeys(email);
        passwordTextfield.sendKeys(password);
        loginBtn.click();
    }

}
