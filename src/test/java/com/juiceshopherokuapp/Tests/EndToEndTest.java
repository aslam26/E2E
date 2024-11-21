package com.juiceshopherokuapp.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created By Aslam Mujawar on 20-11-2024
 */

public class EndToEndTest extends BaseTest{

/****
 Automation Test Strategy(End to End Scenario UI Testing)

1. User will navigate to the website and close all the pop-ups first.
2. User will click on Login link and click on Not yet a customer link and register himself on the website.
3. Once the Registration is successful, registered user will login in the website.
4. After successful Login, User will add an Apple Juice and a Banana Juice to the Basket.
5. After asserting the messages for items added to basket, user will check for the count of items displayed on top of Your Basket link.
6. User will click on Your Basket link and check the order details and click on Checkout.
7. User will enter a new Address for Delivery and select it to process further.
8. User will continue further to Add Card for Payment and select the card added to make payment.
9. On the Order Summary page, user will verify all the details like Name, Address, Order details and total amount to be paid and place order. User will also check that the count on the Your Basket link should be zero as order is already placed successfully.
10. User will re-check the details on Order confirmation page and check for Thank You message, order confirmation and delivery message.
 ***/

    @Test(priority = 1)
    public void VerifyUserIsNavigatedToWebSite(){
        test=extent.createTest("Verify user is navigate to the website and close all the pop-ups first.");
        mainPage.openLoginPage();
    }

    @Test(dependsOnMethods = "VerifyUserIsNavigatedToWebSite", priority = 2)
    public void verifyUserIsRegistered(){
        test=extent.createTest("Verify user click on Login link and click on Not yet a customer link and Registration is successful.");
        email= userData.getEmail();
        password=userData.getPassword();
        regPage.registerUser(email,password, " Name of your favorite pet? ",userData.getAnswer());
        System.out.println(regPage.getSuccessMessage());
        Assert.assertEquals(regPage.getSuccessMessage(),"Registration completed successfully. You can now log in.","message mismatch");
    }

    @Test(dependsOnMethods = "VerifyUserIsNavigatedToWebSite", priority = 3)
    public void verifyUserIsLoggedIn(){
        test=extent.createTest("Verify Registration is successful, registered user will login in the website");
        loginPage.performLogin(email,password);
        mainPage.accountLink.click();
        Assert.assertTrue(loginPage.logoutBtn.isDisplayed());
    }

    @Test(dependsOnMethods ="verifyUserIsLoggedIn")
    public void verifyaddProductToCart(){
        test=extent.createTest("Verify User is able to add an Apple Juice and a Banana Juice to the Basket.");
        productPage.addAppleJuiceToCart();
        Assert.assertEquals(productPage.getSuccessMessage(),"Placed Apple Juice (1000ml) into basket.");
        productPage.addBananaJuiceAddToCart();
        Assert.assertEquals(productPage.getSuccessMessage(),"Placed Banana Juice (1000ml) into basket.");
        Assert.assertEquals(productPage.getBasketCount(2),"2");
        appleJuiceText=productPage.getAppleJuiceTextFromProduct();
        appleJuicePrice=productPage.getAppleJuicePriceFromProduct();
        bananaJuiceText=productPage.getBananaJuiceTextFromProduct();
        bananaJuicePrice=productPage.getBananaJuicePriceFromProduct();
    }

    @Test(dependsOnMethods = "verifyaddProductToCart")
    public void verifyOrders(){
        test=extent.createTest("Verify user clicks on Your Basket link and check the order details and click on Checkout");
        yourBasketPage.clickYourBasket();
        Assert.assertEquals(yourBasketPage.getAppleJuiceTextFromOrders(),appleJuiceText);
        Assert.assertEquals(yourBasketPage.getAppleJuiceQuantity(),1);
        Assert.assertEquals(yourBasketPage.getAppleJuicePriceFromOrders(),appleJuicePrice);
        Assert.assertEquals(yourBasketPage.getBananaJuiceTextFromOrders(),bananaJuiceText);
        Assert.assertEquals(yourBasketPage.getBananaPriceFromOrders(),bananaJuicePrice);
        Assert.assertEquals(yourBasketPage.getBananaJuiceQtyFromOrders(),1);
        double totalPrice=appleJuicePrice+bananaJuicePrice;
        Assert.assertEquals(yourBasketPage.getTotalPriceFromOrders(),totalPrice);
        yourBasketPage.clickCheckOut();
        Assert.assertEquals(yourBasketPage.getSelectAnAddress(),"Select an address");
    }

    @Test(dependsOnMethods = "verifyOrders")
    public void verifyNewAddressIsAdded(){
        test=extent.createTest("Verify User enter a new Address for Delivery");
        addNewAddressPage.clickAddNewAddress();
        addNewAddressPage.setAddNewAddress(userData.getCountry(),userData.getName(),userData.getMobileNo(),userData.getZipCode(),userData.getAddress(),userData.getCity(),userData.getState());
        deliveryAddress=userData.getAddress();
        customerName=userData.getName();
        cellPhone=userData.getMobileNo();
        city=userData.getCity();
        state=userData.getState();
        country=userData.getCountry();
        zipcode=userData.getZipCode();
        addNewAddressPage.clickSubmitButton();
        Assert.assertEquals(addNewAddressPage.getTitleSelectAddress(),"Select an address");
    }

    @Test(dependsOnMethods = "verifyNewAddressIsAdded")
    public void verifyUserSelectsAddress(){
        test=extent.createTest("Verify user is able to select address");
        selectAddressPage.selectRadioBtn();
        selectAddressPage.clickContinueBtn();
    }

    @Test(dependsOnMethods = "verifyUserSelectsAddress")
    public void verifyDeliveryOptionsSelection(){
        test=extent.createTest("Verify user is able select delivery to process further");
        String formatedAddress=String.format("%s, %s, %s, %s",deliveryAddress,city,state,zipcode);
        Assert.assertEquals(deliveryOptionsPage.getDeliveryAddress(),formatedAddress);
        Assert.assertEquals(deliveryOptionsPage.getDeliveryName(),customerName);
        Assert.assertEquals(deliveryOptionsPage.getDeliveryCountry(),country);
        deliveryOptionsPage.selectDeliveryOption();
        deliveryOptionsPage.clickContinueBtn();
    }


    @Test(dependsOnMethods = "verifyDeliveryOptionsSelection")
    public void verifyPaymentCardDetailsAdded(){
        test=extent.createTest("Verify user is able to continue further to Add Card for Payment and select the card added to make payment.");
        myPaymentOptionPage.addNewCardDetails(customerName,userData.getCreditCardNumber(),userData.getExpiryMonth(),userData.getExpiryYear());
        Assert.assertEquals(myPaymentOptionPage.addDetailsMessage(),"Your card ending with 3456 has been saved for your convenience.");
        myPaymentOptionPage.setSelectCardRadioBtn();
        myPaymentOptionPage.clickContinueBtn();
    }

    @Test(dependsOnMethods = "verifyPaymentCardDetailsAdded")
    public void verifyOrderSummary(){
        test=extent.createTest("Verify user is on the Order Summary page, user will verify all the details total amount to be paid and place order.");
        double totalPrice=appleJuicePrice+bananaJuicePrice;
        Assert.assertEquals(orderSummaryPage.getDeliveryItemPriceFromOrderSummary("3.98"),totalPrice);
        Assert.assertEquals(orderSummaryPage.getDeliveryAmountFromOrderSummary(),0.0);
        double finalTotalPrice=totalPrice+orderSummaryPage.getDeliveryAmountFromOrderSummary();
        Assert.assertEquals(orderSummaryPage.getTotalPriceFromOrderSummary(),finalTotalPrice);
        orderSummaryPage.placeOrderAndPay();
    }

    @Test(dependsOnMethods = "verifyOrderSummary" )
    public void verifyOrderConfirmation(){
        test=extent.createTest("Verify user re-check the details on Order confirmation page and check for Thank You message, order confirmation and delivery message and verify Your Basket link should be zero as order is already placed successfully");
        Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(),"Your order has been placed and is being processed. You can check for status updates on our Track Orders page.");
        Assert.assertEquals(orderConfirmationPage.getOrderDeliveryMessage(),"Your order will be delivered in 5 days.");
        Assert.assertEquals(orderConfirmationPage.getThanksMessage(),"Thank you for your purchase!");
        Assert.assertEquals(productPage.getBasketCount(0),"0");
    }











}
