package com.juiceshopherokuapp.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.juiceshopherokuapp.CommonLibs.Implementation.CommonDrivers;
import com.juiceshopherokuapp.CommonLibs.Utils.UserData;
import com.juiceshopherokuapp.CommonLibs.Utils.UserDataBuilder;
import com.juiceshopherokuapp.Pages.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created By Aslam Mujawar on 20-11-2024
 */

public class BaseTest {
    WebDriver driver;
    String currentWorkingDir=System.getProperty("user.dir");
    public static Properties prop;
    CommonDrivers cmdDriver;
    MainPage mainPage;
    protected ExtentTest test;
    protected static ExtentReports extent;
    ExtentSparkReporter reporters;
    FileInputStream ip;
    String baseUrl;
    UserData userData;
    public String email;
    public String password;
    RegistrationPage regPage;
    LoginPage loginPage;
    ProductPage productPage;
    String appleJuiceText;
    String bananaJuiceText;
    double appleJuicePrice;
    double bananaJuicePrice;
    String deliveryAddress;
    String cellPhone;
    String customerName;
    String state;
    String country;
    String zipcode;
    String city;
    YourBasketPage yourBasketPage;
    AddNewAddressPage addNewAddressPage;
    SelectAddressPage selectAddressPage;
    DeliveryOptionsPage deliveryOptionsPage;
    MyPaymentOptionPage myPaymentOptionPage;
    OrderSummaryPage orderSummaryPage;
    OrderConfirmationPage orderConfirmationPage;

    @BeforeSuite
    public void preSetup() throws Exception {
        reporters=new ExtentSparkReporter(currentWorkingDir+"/reports/TestReport.html");
        extent=new ExtentReports();
        extent.attachReporter(reporters);
        extent.setSystemInfo("Environment","QA");

        prop=new Properties();
        ip=new FileInputStream(currentWorkingDir+"/Config/config.properties");
        prop.load(ip);
    }

    @Parameters("browserType")
    @BeforeClass
    public void setUp(String browserType) throws Exception {
        cmdDriver=new CommonDrivers(browserType);
        driver= cmdDriver.getDriver();

        baseUrl=prop.getProperty("baseUrl");
        cmdDriver.navigateBaseUrl(baseUrl);

        mainPage=new MainPage(driver);
        userData=UserDataBuilder.createUserData();
        regPage=new RegistrationPage(driver);
        loginPage=new LoginPage(driver);
        productPage=new ProductPage(driver);
        yourBasketPage=new YourBasketPage(driver);
        addNewAddressPage=new AddNewAddressPage(driver);
        selectAddressPage=new SelectAddressPage(driver);
        deliveryOptionsPage=new DeliveryOptionsPage(driver);
        myPaymentOptionPage=new MyPaymentOptionPage(driver);
        orderSummaryPage=new OrderSummaryPage(driver);
        orderConfirmationPage=new OrderConfirmationPage(driver);
    }

    @AfterMethod
    public void postMethod(ITestResult result) throws Exception{
        String testCaseName = result.getName();
        long executionTime=System.currentTimeMillis();
        String screenshotFilename=currentWorkingDir+"/Failed_Screenshots/"+testCaseName+executionTime+".jpeg";
        if(result.getStatus()==ITestResult.FAILURE){
            test.log(Status.FAIL,"Test case failed");
            TakesScreenshot camera=(TakesScreenshot) driver;
            File captureScreenshot=camera.getScreenshotAs(OutputType.FILE);
            File destination=new File(screenshotFilename);
            FileUtils.copyFile(captureScreenshot,destination);
            System.out.println("Screenshot Taken");
        } else if (result.getStatus()==ITestResult.SUCCESS) {
            test.log(Status.PASS,"Test case passed");
        } else if (result.getStatus()==ITestResult.SKIP) {
            test.log(Status.SKIP,"Test case skipped execution");
        }else {
            throw new Exception("Unknown Error, please check logs");
        }

    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        cmdDriver.closeAllBrowser();
    }

    @AfterSuite
    public void createReport(){
        extent.flush();
        System.out.println("Report is created");
    }

}
