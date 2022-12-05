package com.paymentservice.paymentService.storage.manger;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.payloads.request.wallet.WalletDetailsRequest;
import com.paymentservice.paymentService.storage.WalletDetails;
import com.paymentservice.paymentService.storage.repositories.WalletDetailsRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component

@Service
public class WalletDetailsManager {

  @Autowired
  private WalletDetailsRepo walletDetailsRepo;

  public void createOrUpdateWalletDetails(final WalletDetailsRequest walletDetailsRequest) {

    final WalletDetails walletData = requestObjToWallet(walletDetailsRequest);
    walletDetailsRepo.save(walletData);
  }


  public WalletDetails getWalletDetailsById(final String phoneNumber) {

    if (walletDetailsRepo.existsById(phoneNumber)) {
      return walletDetailsRepo.getReferenceById(phoneNumber);
    } else {
      throw new AppException(ErrorCode.WALLET_DOES_NOT_EXIST,
          "Wallet with phoneNumber Does Not Exists", null);
    }
  }

  public void deleteWalletDetails(final String phoneNumber) {

    walletDetailsRepo.delete(walletDetailsRepo.getReferenceById(phoneNumber));
  }

  private WalletDetails requestObjToWallet(final WalletDetailsRequest walletDetailsRequest) {

    return WalletDetails.builder().phoneNumber(walletDetailsRequest.getPhoneNumber())
        .dateOfBirth(walletDetailsRequest.getDateOfBirth())
        .walletBalance(walletDetailsRequest.getWalletBalance())
        .mailId(walletDetailsRequest.getMailId()).build();

  }

  public WalletDetailsRequest walletToRequestObj(final WalletDetails walletDetails) {

    return WalletDetailsRequest.builder().phoneNumber(walletDetails.getPhoneNumber())
        .dateOfBirth(walletDetails.getDateOfBirth()).walletBalance(walletDetails.getWalletBalance())
        .mailId(walletDetails.getMailId()).build();

  }

}
