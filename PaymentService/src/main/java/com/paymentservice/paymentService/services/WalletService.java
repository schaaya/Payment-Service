package com.paymentservice.paymentService.services;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.models.OperationType;
import com.paymentservice.paymentService.payloads.request.wallet.AddMoneyToWalletRequest;
import com.paymentservice.paymentService.payloads.request.wallet.SendMoneyFromWalletBaseRequest;
import com.paymentservice.paymentService.payloads.request.wallet.SendMoneyFromWalletTransactionHandlerVisitor;
import com.paymentservice.paymentService.payloads.request.wallet.WalletDetailsRequest;
import com.paymentservice.paymentService.payloads.response.wallet.SendMoneyFromWalletResponseBuilder;
import com.paymentservice.paymentService.payloads.response.wallet.WalletDetailsResponse;
import com.paymentservice.paymentService.storage.WalletDetails;
import com.paymentservice.paymentService.storage.manger.ClientRequestMappingManager;
import com.paymentservice.paymentService.storage.manger.RequestDetailsManager;
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
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Component
@Configuration
@ComponentScan
@Service
public class WalletService {

  @Autowired
  private SendMoneyFromWalletTransactionHandlerVisitor sendMoneyFromWalletTransactionHandlerVisitor;

  @Autowired
  private BankingService bankingService;

  @Autowired
  private WalletDetailsManager walletDetailsManager;

  @Autowired
  private ClientRequestMappingManager clientRequestMappingManager;

  @Autowired
  private SessionDetailsManager sessionDetailsManager;

  @Autowired
  private RequestDetailsManager requestDetailsManager;

  public WalletDetailsResponse getWalletDetails(final WalletDetailsRequest walletDetailsRequest,
      final String sessionId) {

    //generate requestId REQ

    final String requestId = RequestUtils.generateRequestId();

    clientRequestMappingManager.saveClientRequestMapping(walletDetailsRequest.getClientRequestId(),
        requestId);
    requestDetailsManager.validateAndSaveRequestDetails(requestId,
        walletDetailsRequest.getClientRequestId(),
        "GET_WALLET_DETAILS");

    final var currentUserPhoneNumber = sessionDetailsManager.getPhoneNumberFromSessionId(sessionId);
    //fetch details from db

    final WalletDetails currentUserWalletDetails = walletDetailsManager.getWalletDetailsById(
        currentUserPhoneNumber);

    return WalletDetailsResponse.builder()
        .walletDetails(currentUserWalletDetails)
        .requestId(requestId)
        .build();
  }

  public WalletDetailsResponse addMoneyToWallet(
      final AddMoneyToWalletRequest addMoneyToWalletRequest,
      final String sessionId) {

    final String requestId = RequestUtils.generateRequestId();

    clientRequestMappingManager.saveClientRequestMapping(
        addMoneyToWalletRequest.getClientRequestId(), requestId);
    requestDetailsManager.validateAndSaveRequestDetails(requestId,
        addMoneyToWalletRequest.getClientRequestId(),
        "ADD_MONEY_TO_WALLET");

    final var currentUserPhoneNumber = sessionDetailsManager.getPhoneNumberFromSessionId(sessionId);

    final WalletDetails currentUserWalletDetails = walletDetailsManager.getWalletDetailsById(
        currentUserPhoneNumber);

    bankingService.validateAndDoOperation(addMoneyToWalletRequest.getBankDetails(),
        addMoneyToWalletRequest.getAmountToBeAdded(), OperationType.DEDUCT);

    final int newBalance =
        addMoneyToWalletRequest.getAmountToBeAdded() + currentUserWalletDetails.getWalletBalance();

    currentUserWalletDetails.setWalletBalance(newBalance);

    walletDetailsManager.createOrUpdateWalletDetails(
        walletDetailsManager.walletToRequestObj(currentUserWalletDetails));

    return WalletDetailsResponse.builder()
        .walletDetails(currentUserWalletDetails)
        .requestId(requestId)
        .build();
  }

  public SendMoneyFromWalletResponseBuilder sendMoneyFromWallet(
      final SendMoneyFromWalletBaseRequest sendMoneyFromWalletBaseRequest,
      final String sessionId) {

    //generate requestId REQ
    final String requestId = RequestUtils.generateRequestId();

    clientRequestMappingManager.saveClientRequestMapping(
        sendMoneyFromWalletBaseRequest.getClientRequestId(),
        requestId);
    requestDetailsManager.validateAndSaveRequestDetails(requestId,
        sendMoneyFromWalletBaseRequest.getClientRequestId(), "SEND_MONEY_FROM_WALLET");

    final var currentUserPhoneNumber = sessionDetailsManager.getPhoneNumberFromSessionId(sessionId);

    final WalletDetails currentUserWalletDetails = walletDetailsManager.getWalletDetailsById(
        currentUserPhoneNumber);

    if (currentUserWalletDetails.getWalletBalance()
        < sendMoneyFromWalletBaseRequest.getAmountToBeSent()) {

      throw new AppException(ErrorCode.LOW_BALANCE,
          "User does not have enough balance to complete the transaction", null);
    }

    final int newBalance =
        currentUserWalletDetails.getWalletBalance()
            - sendMoneyFromWalletBaseRequest.getAmountToBeSent();

    currentUserWalletDetails.setWalletBalance(newBalance);

    walletDetailsManager.createOrUpdateWalletDetails(
        walletDetailsManager.walletToRequestObj(currentUserWalletDetails));

    final var sendMoneyFromWalletResponseBuilderBuilder = sendMoneyFromWalletBaseRequest.accept(
        sendMoneyFromWalletTransactionHandlerVisitor, currentUserWalletDetails);

    return sendMoneyFromWalletResponseBuilderBuilder.requestId(requestId)
        .remainingMoney(currentUserWalletDetails.getWalletBalance())
        .build();
  }
}
