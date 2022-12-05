package com.paymentservice.paymentService.services;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.payloads.request.login.LoginRequest;
import com.paymentservice.paymentService.payloads.response.LoginResponse;
import com.paymentservice.paymentService.storage.WalletCredentials;
import com.paymentservice.paymentService.storage.manger.ClientRequestMappingManager;
import com.paymentservice.paymentService.storage.manger.RequestDetailsManager;
import com.paymentservice.paymentService.storage.manger.SessionDetailsManager;
import com.paymentservice.paymentService.storage.manger.WalletCredentialsManager;
import com.paymentservice.paymentService.utils.RequestUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Configuration
@ComponentScan
@Service
public class AuthenticationService {

  @Autowired
  private RequestDetailsManager requestDetailsManager;

  @Autowired
  private SessionDetailsManager sessionDetailsManager;

  @Autowired
  private WalletCredentialsManager walletCredentialsManager;

  @Autowired
  private ClientRequestMappingManager clientRequestMappingManager;

  public LoginResponse login(final LoginRequest loginRequest) {

    final String requestId = RequestUtils.generateRequestId();
    final String sessionId = RequestUtils.generateSessionId();

    walletCredentialsManager.createOrUpdateWalletCredentials(loginRequest);
    authenticateUserWithPassword(loginRequest.getPhoneNumber(), loginRequest.getPassword());

    clientRequestMappingManager.saveClientRequestMapping(loginRequest.getClientRequestId(),
        requestId);
    requestDetailsManager.validateAndSaveRequestDetails(requestId,
        loginRequest.getClientRequestId(), "LOGIN");
    sessionDetailsManager.saveSessionDetails(sessionId, requestId, loginRequest.getPhoneNumber());
    return LoginResponse.builder()
        .sessionId(sessionId)
        .requestId(requestId)
        .build();
  }

  @SneakyThrows
  private void authenticateUserWithPassword(final String phoneNumber,
      final String password) {

    final WalletCredentials walletCredentials = walletCredentialsManager.getWalletCredentials(
        phoneNumber);

    if (!walletCredentials.getPassword()
        .equals(password)) {
      throw new AppException(ErrorCode.USER_UNAUTHORIZED, "Password incorrect", null);
    }


  }

  public void logout(final String sessionId) {

    final String requestId = RequestUtils.generateRequestId();
    requestDetailsManager.validateAndSaveRequestDetails(requestId, null, "LOGOUT");

    sessionDetailsManager.deleteSession(sessionId);
  }
}
