package com.juiceshopherokuapp.CommonLibs.Utils;

import com.github.javafaker.Faker;

public class UserDataBuilder {

    protected static Faker faker=new Faker();

    public static UserData createUserData() {
        UserData usd = new UserData();
        usd.setEmail(faker.internet().emailAddress());
        usd.setPassword(faker.internet().password(8, 20, true, true, true));
        usd.setAnswer(faker.animal().name());
        usd.setCountry(faker.country().name());
        usd.setName(faker.name().name());
        String mobileNum=faker.numerify("##########");
        String digitMobileNum= mobileNum.replaceAll("[^0-9]", "");
        usd.setMobileNo(digitMobileNum);
        usd.setZipCode(faker.numerify("#######"));
        usd.setAddress(faker.address().fullAddress());
        usd.setCity(faker.address().city());
        usd.setState(faker.address().state());
        usd.setCreditCardNumber("1234567890123456");
        int rawExpiryMonth=faker.number().numberBetween(1,12);
        String expiryMonth=String.valueOf(rawExpiryMonth);
        usd.setExpiryMonth(expiryMonth);
        int rawExpiryYear=faker.number().numberBetween(2081,2098);
        String expiryYear=String.valueOf(rawExpiryYear);
        usd.setExpiryYear(expiryYear);
        return usd;
    }
}
