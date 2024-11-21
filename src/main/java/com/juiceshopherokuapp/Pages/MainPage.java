package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@aria-label='Close Welcome Banner']")
    WebElement welcomeDimissBtn;

    @FindBy(xpath = "//a[normalize-space()='Me want it!']")
    WebElement meWantIBtn;

    @FindBy(xpath = "//span[normalize-space()='Account']")
    public WebElement accountLink;

    @FindBy(css = "button#navbarLoginButton")
    WebElement loginLink;

    public void openLoginPage(){
        Actions actions=new Actions(driver);
        meWantIBtn.click();
        actions.pause(Duration.ofSeconds(5)).moveToElement(welcomeDimissBtn).click().build().perform();
        accountLink.click();
        loginLink.click();
    }



}
