package com.javatechie.model;

import jakarta.persistence.Column;

public class PaymentInfo {
    @Column(name = "cardHolder_name")
    private String cardHolderName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_Date")
    private String expirationDate;

    @Column(name = "cvv")
    private String cvv;
}
