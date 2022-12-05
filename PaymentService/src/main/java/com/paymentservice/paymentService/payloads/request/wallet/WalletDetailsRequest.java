package com.paymentservice.paymentService.payloads.request.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ComponentScan
@Component
@Builder
public class WalletDetailsRequest {

  // private int id;
  private String clientRequestId;
  private String phoneNumber;
  private Integer walletBalance;
  private String name;
  private String mailId;
  private String dateOfBirth;
}
