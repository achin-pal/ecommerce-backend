package com.javatechie.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

  private String paymentMethod;
  private String paymentStatus;
  private String paymentId;
  private String razorpayPaymentLinkId;
  private String razorpayPaymentLinkReferenceId;
  private String razorpayPaymentLinkStatus;
  private String razorpayPaymentId;



}