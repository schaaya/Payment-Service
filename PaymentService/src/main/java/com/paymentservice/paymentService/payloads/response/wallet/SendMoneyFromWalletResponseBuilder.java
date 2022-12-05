package com.paymentservice.paymentService.payloads.response.wallet;

import com.paymentservice.paymentService.payloads.common.SendMoneyTransactionType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ComponentScan
public class SendMoneyFromWalletResponseBuilder {

  private Integer remainingMoney;

  private String transactionId;

  private String requestId;

  private SendMoneyTransactionType transactionType;

  @Generated(value = GenerationTime.ALWAYS)
  private Date timeStamp;
}
