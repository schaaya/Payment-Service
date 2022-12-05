package com.paymentservice.paymentService.payloads.request.wallet;

import com.paymentservice.paymentService.payloads.common.SendMoneyTransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ComponentScan
@NoArgsConstructor
@AllArgsConstructor
public class SendMoneyFromWalletToQr extends SendMoneyFromWalletBaseRequest {


  private String qrUrl;

  public SendMoneyFromWalletToQr(final String qrUrl,
      final Integer amountToBeSent,
      final String clientRequestId) {
    super(SendMoneyTransactionType.QR, clientRequestId, amountToBeSent);
    this.qrUrl = qrUrl;
  }


  @Override
  public <T, J> T accept(final SendMoneyFromWalletBaseRequestVisitor<T, J> visitor,
      final J data) {
    return visitor.visitQr(this, data);
  }
}
