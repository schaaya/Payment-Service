package com.paymentservice.paymentService.payloads.response.wallet;

import com.paymentservice.paymentService.storage.WalletDetails;
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
public class WalletDetailsResponse {


  private WalletDetails walletDetails;

  private String requestId;

  @Generated(value = GenerationTime.ALWAYS)
  private Date timeStamp;


}
