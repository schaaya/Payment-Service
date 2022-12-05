package com.paymentservice.paymentService.storage.manger;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.payloads.request.login.LoginRequest;
import com.paymentservice.paymentService.storage.WalletCredentials;
import com.paymentservice.paymentService.storage.repositories.WalletCredentialsRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Component
@Configuration
@ComponentScan
//@Scope("Singleton")
@Service
public class WalletCredentialsManager {
  @Autowired
  private WalletCredentialsRepo walletCredentialsRepo;

  public void createOrUpdateWalletCredentials(final LoginRequest loginRequest) {

    final WalletCredentials walletCredentials = requestObjToWallet(loginRequest);
    walletCredentialsRepo.save(walletCredentials);
  }



  public WalletCredentials getWalletCredentials(final String phoneNumber) throws Exception {
    if (walletCredentialsRepo.existsById(phoneNumber)) {
      return walletCredentialsRepo.getReferenceById(phoneNumber);
    } else {
      throw new AppException(ErrorCode.USER_DOES_NOT_EXIST, "User Does Not Exists", null);
    }
  }

  private WalletCredentials requestObjToWallet(final LoginRequest loginRequest) {

    return WalletCredentials.builder().phoneNumber(loginRequest.getPhoneNumber())
        .dateOfBirth(loginRequest.getDateOfBirth())
        .password(loginRequest.getPassword())
        .mailId(loginRequest.getMailId()).build();

  }

}
