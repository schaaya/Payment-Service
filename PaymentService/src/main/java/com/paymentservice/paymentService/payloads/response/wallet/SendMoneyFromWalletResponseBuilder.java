package com.paymentservice.paymentService.payloads.response.wallet;

import com.paymentservice.paymentService.payloads.common.SendMoneyTransactionType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
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

  public static SendMoneyFromWalletResponseBuilderBuilder builder() {
    return new SendMoneyFromWalletResponseBuilderBuilder();
  }

  public static class SendMoneyFromWalletResponseBuilderBuilder {

    private Integer remainingMoney;
    private String transactionId;
    private String requestId;
    private SendMoneyTransactionType transactionType;
    private Date timeStamp;

    SendMoneyFromWalletResponseBuilderBuilder() {
    }

    public SendMoneyFromWalletResponseBuilderBuilder remainingMoney(
        Integer remainingMoney) {
      this.remainingMoney = remainingMoney;
      return this;
    }

    public SendMoneyFromWalletResponseBuilderBuilder transactionId(
        String transactionId) {
      this.transactionId = transactionId;
      return this;
    }

    public SendMoneyFromWalletResponseBuilderBuilder requestId(
        String requestId) {
      this.requestId = requestId;
      return this;
    }

    public SendMoneyFromWalletResponseBuilderBuilder transactionType(
        SendMoneyTransactionType transactionType) {
      this.transactionType = transactionType;
      return this;
    }

    public SendMoneyFromWalletResponseBuilderBuilder timeStamp(
        Date timeStamp) {
      this.timeStamp = timeStamp;
      return this;
    }

    public SendMoneyFromWalletResponseBuilder build() {
      return new SendMoneyFromWalletResponseBuilder(remainingMoney, transactionId, requestId,
          transactionType, timeStamp);
    }

    public String toString() {
      return
          "SendMoneyFromWalletResponseBuilder.SendMoneyFromWalletResponseBuilderBuilder(remainingMoney="
              + this.remainingMoney + ", transactionId=" + this.transactionId + ", requestId="
              + this.requestId + ", transactionType=" + this.transactionType + ", timeStamp="
              + this.timeStamp + ")";
    }
  }
}
