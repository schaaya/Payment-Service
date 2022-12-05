package com.paymentservice.paymentService.payloads.request.wallet;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.models.OperationType;
import com.paymentservice.paymentService.payloads.common.SendMoneyTransactionType;
import com.paymentservice.paymentService.payloads.response.wallet.SendMoneyFromWalletResponseBuilder;
import com.paymentservice.paymentService.payloads.response.wallet.SendMoneyFromWalletResponseBuilder.SendMoneyFromWalletResponseBuilderBuilder;
import com.paymentservice.paymentService.services.BankingService;
import com.paymentservice.paymentService.storage.WalletDetails;
import com.paymentservice.paymentService.storage.manger.SessionDetailsManager;
import com.paymentservice.paymentService.storage.manger.WalletDetailsManager;
import com.paymentservice.paymentService.utils.RequestUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ComponentScan
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SendMoneyFromWalletTransactionHandlerVisitor implements
    SendMoneyFromWalletBaseRequestVisitor<SendMoneyFromWalletResponseBuilderBuilder, WalletDetails> {

  // QRService which will transfer money from wallet to QR
  //BankingService

  @Autowired
  private WalletDetailsManager walletDetailsManager;

  @Autowired
  private BankingService bankingService;

  @Autowired
  private SessionDetailsManager sessionDetailsManager;


  @Override
  public SendMoneyFromWalletResponseBuilderBuilder visitQr(
      final SendMoneyFromWalletToQr sendMoneyFromWalletToQr,
      final WalletDetails walletDetails) {
    //QRService.sendMoney(QRsendMoney);

    //deduct money from wallet
    return SendMoneyFromWalletResponseBuilder.builder()
        .transactionId(RequestUtils.generateTransactionId())
        .transactionType(SendMoneyTransactionType.QR);
  }

  @Override
  public SendMoneyFromWalletResponseBuilderBuilder visitBank(
      final SendMoneyFromWalletToBank sendMoneyFromWalletToBank,
      final WalletDetails walletDetails) {
    //Banking Service

    try {
      bankingService.validateAndDoOperation(sendMoneyFromWalletToBank.getBankDetails(),
          sendMoneyFromWalletToBank.getAmountToBeSent(), OperationType.ADD);
    } catch (final Exception e) {

      walletDetails.setWalletBalance(
          walletDetails.getWalletBalance() - sendMoneyFromWalletToBank.getAmountToBeSent());

      walletDetailsManager.createOrUpdateWalletDetails(
          walletDetailsManager.walletToRequestObj(walletDetails));

      throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR,
          "Banking service error! Try again later. ", null);

    }

    return SendMoneyFromWalletResponseBuilder.builder()
        .transactionId(RequestUtils.generateTransactionId())
        .transactionType(SendMoneyTransactionType.BANK);
  }

  @Override
  public SendMoneyFromWalletResponseBuilderBuilder visitWallet(
      final SendMoneyFromWalletToWallet sendMoneyFromWalletToWallet,
      final WalletDetails walletDetails) {
    try {

      final WalletDetails recieverWalletDetails = walletDetailsManager.getWalletDetailsById(
          sendMoneyFromWalletToWallet.getReceiverPhoneNumber());

      recieverWalletDetails.setWalletBalance(
          recieverWalletDetails.getWalletBalance()
              + sendMoneyFromWalletToWallet.getAmountToBeSent());

    } catch (final Exception e) {
      walletDetails.setWalletBalance(
          walletDetails.getWalletBalance() - sendMoneyFromWalletToWallet.getAmountToBeSent());

      walletDetailsManager.createOrUpdateWalletDetails(
          walletDetailsManager.walletToRequestObj(walletDetails));

      throw new AppException(ErrorCode.USER_DOES_NOT_EXIST,
          "Destination User does Not Exists! Try again later. ",
          null);
    }

    return SendMoneyFromWalletResponseBuilder.builder()
        .transactionId(RequestUtils.generateTransactionId())
        .transactionType(SendMoneyTransactionType.WALLET);
  }
}
