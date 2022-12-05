package com.paymentservice.paymentService.payloads.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@AllArgsConstructor
@Configuration
@ComponentScan
@NoArgsConstructor
public class BankDetails {

  String accountNumber;

  String routingNumber;
}
