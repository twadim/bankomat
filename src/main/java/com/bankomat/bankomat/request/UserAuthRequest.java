package com.bankomat.bankomat.request;

public class UserAuthRequest
{


    public UserAuthRequest(){

    }

    public UserAuthRequest(String cardNumber, String password){

        this.cardNumber = cardNumber;
        this.password = password;

    }

    private String cardNumber;
    private String password;




    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
