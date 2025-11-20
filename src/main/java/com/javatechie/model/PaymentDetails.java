package com.javatechie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentDetails {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  private String paymentMethod;
  private String paymentStatus;
  private String paymentId;
  private String razorpayPaymentLinkId;
  private String razorpayPaymentLinkReferenceId;
  private String razorpayPaymentLinkStatus;
  private String razorpayPaymentId;



}