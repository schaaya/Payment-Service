package com.paymentservice.paymentService.payloads.request.wallet;

import com.paymentservice.paymentService.payloads.common.BankDetails;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Configuration
@ComponentScan
public class AddMoneyToWalletRequest {

  @Valid BankDetails bankDetails;
  private Integer amountToBeAdded;
  private String clientRequestId;


}
