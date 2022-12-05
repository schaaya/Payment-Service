package com.paymentservice.paymentService.payloads.request.wallet;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.paymentservice.paymentService.payloads.common.SendMoneyTransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Configuration
@ComponentScan
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SendMoneyFromWalletToQr.class, name = SendMoneyTransactionType.QR_TEXT),
    @JsonSubTypes.Type(value = SendMoneyFromWalletToBank.class, name = SendMoneyTransactionType.BANK_TEXT),
    @JsonSubTypes.Type(value = SendMoneyFromWalletToWallet.class, name = SendMoneyTransactionType.WALLET_TEXT)})
public abstract class SendMoneyFromWalletBaseRequest {

  private SendMoneyTransactionType type;

  private String clientRequestId;

  private Integer amountToBeSent;

  protected SendMoneyFromWalletBaseRequest(final SendMoneyTransactionType type) {
    this.type = type;
  }

  public abstract <T, J> T accept(SendMoneyFromWalletBaseRequestVisitor<T, J> visitor,
      J data);
}

