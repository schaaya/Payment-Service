package com.paymentservice.paymentService.resources;

import com.paymentservice.paymentService.payloads.request.wallet.AddMoneyToWalletRequest;
import com.paymentservice.paymentService.payloads.request.wallet.SendMoneyFromWalletBaseRequest;
import com.paymentservice.paymentService.payloads.request.wallet.WalletDetailsRequest;
import com.paymentservice.paymentService.payloads.response.wallet.SendMoneyFromWalletResponseBuilder;
import com.paymentservice.paymentService.payloads.response.wallet.WalletDetailsResponse;
import com.paymentservice.paymentService.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/wallet/{sessionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletResource {


  @Autowired
  private final WalletService walletService;


  @GetMapping(path = "/walletDetails")
  ResponseEntity<WalletDetailsResponse> getWalletDetails(
      @RequestBody final WalletDetailsRequest walletDetailsRequest,
      @PathVariable final String sessionId) {
    return new ResponseEntity<>(walletService.getWalletDetails(walletDetailsRequest, sessionId),
        HttpStatus.OK);
  }

  @PostMapping(path = "/addMoneyToWallet")
  ResponseEntity<WalletDetailsResponse> addMoneyToWallet(
      @RequestBody final AddMoneyToWalletRequest addMoneyToWalletRequest,
      @PathVariable final String sessionId) {
    return new ResponseEntity<>(walletService.addMoneyToWallet(addMoneyToWalletRequest, sessionId),
        HttpStatus.OK);
  }

  @PostMapping(path = "/sendMoneyFromWallet/wallet")
  SendMoneyFromWalletResponseBuilder sendMoneyFromWalletToWallet(
      @RequestBody final SendMoneyFromWalletBaseRequest sendMoneyFromWalletBaseRequest,
      @PathVariable final String sessionId) {

    return walletService.sendMoneyFromWallet(sendMoneyFromWalletBaseRequest, sessionId);
  }

  @PostMapping(path = "/sendMoneyFromWallet/qr")
  SendMoneyFromWalletResponseBuilder sendMoneyFromWalletToQr(
      @RequestBody final SendMoneyFromWalletBaseRequest sendMoneyFromWalletBaseRequest,
      @PathVariable final String sessionId) {
    return walletService.sendMoneyFromWallet(sendMoneyFromWalletBaseRequest, sessionId);
  }

  @PostMapping(path = "/sendMoneyFromWallet/bank")
  SendMoneyFromWalletResponseBuilder sendMoneyFromWalletToBank(
      @RequestBody final SendMoneyFromWalletBaseRequest sendMoneyFromWalletBaseRequest,
      @PathVariable final String sessionId) {
    return walletService.sendMoneyFromWallet(sendMoneyFromWalletBaseRequest, sessionId);
  }
}


