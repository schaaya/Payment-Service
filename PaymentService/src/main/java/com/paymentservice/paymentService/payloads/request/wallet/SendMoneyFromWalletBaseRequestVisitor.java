package com.paymentservice.paymentService.payloads.request.wallet;

public interface SendMoneyFromWalletBaseRequestVisitor<T, J> {

  T visitQr(SendMoneyFromWalletToQr sendMoneyFromWalletToQr,
      J data);

  T visitBank(SendMoneyFromWalletToBank sendMoneyFromWalletToBank,
      J data);

  T visitWallet(SendMoneyFromWalletToWallet sendMoneyFromWalletToWallet,
      J data);

}
