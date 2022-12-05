package com.paymentservice.paymentService.payloads.request.wallet;

import com.paymentservice.paymentService.payloads.common.SendMoneyTransactionType;
import javax.validation.Valid;
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
public class SendMoneyFromWalletToWallet extends SendMoneyFromWalletBaseRequest {

  @Valid
  private String receiverPhoneNumber;

  public SendMoneyFromWalletToWallet(final String receiverPhoneNumber,
      final Integer amountToBeSent,
      final String clientRequestId) {
    super(SendMoneyTransactionType.WALLET, clientRequestId, amountToBeSent);
    this.receiverPhoneNumber = receiverPhoneNumber;
  }


  @Override
  public <T, J> T accept(final SendMoneyFromWalletBaseRequestVisitor<T, J> visitor,
      final J data) {
    return visitor.visitWallet(this, data);
  }
}
