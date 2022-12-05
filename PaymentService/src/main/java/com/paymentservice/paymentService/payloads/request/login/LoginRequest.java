package com.paymentservice.paymentService.payloads.request.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ComponentScan
public class LoginRequest {

  private String name;

  private String phoneNumber;

  private String mailId;

  private String password;

  private String dateOfBirth;

  private String clientRequestId;

}
