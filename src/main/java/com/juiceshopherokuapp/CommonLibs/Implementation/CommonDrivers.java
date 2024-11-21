package com.juiceshopherokuapp.CommonLibs.Implementation;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

/**
 * Created By Aslam Mujawar on 20-11-2024
 */

public class CommonDrivers {

    @Getter
    private WebDriver driver;

    public CommonDrivers(String browserType)throws Exception{

        if(browserType.equalsIgnoreCase("chrome")){
            this.driver=new ChromeDriver();
        }else if(browserType.equalsIgnoreCase("edge")){
            this.driver=new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("headless")) {
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--headless");
            this.driver=new ChromeDriver(options);
        }else{
            throw new Exception("Invalid browser type."+browserType);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public void navigateBaseUrl(String baseUrl){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.get(baseUrl);
    }

    public void closeAllBrowser(){
        if(driver != null){
            driver.quit();
        }
    }

}
