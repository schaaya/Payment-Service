package com.paymentservice.paymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.paymentservice.paymentService"})
public class PaymentServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
  }

}
