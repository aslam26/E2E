package com.juiceshopherokuapp.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created By Aslam Mujawar on 20-11-2024
 */

public class BasePage {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        this.actions=new Actions(driver);
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(25));
    }
}
