package com.paymentservice.paymentService.payloads.request.wallet;

import com.paymentservice.paymentService.payloads.common.BankDetails;
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
public class SendMoneyFromWalletToBank extends SendMoneyFromWalletBaseRequest {

  @Valid
  public BankDetails bankDetails;

  public SendMoneyFromWalletToBank(final BankDetails bankDetails,
      final Integer amountToBeSent,
      final String clientRequestId) {
    super(SendMoneyTransactionType.BANK, clientRequestId, amountToBeSent);
    this.bankDetails = bankDetails;
  }

  @Override
  public <T, J> T accept(final SendMoneyFromWalletBaseRequestVisitor<T, J> visitor,
      final J data) {
    return visitor.visitBank(this, data);
  }
}
