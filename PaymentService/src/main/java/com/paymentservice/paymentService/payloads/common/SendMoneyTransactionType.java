package com.paymentservice.paymentService.payloads.common;

public enum SendMoneyTransactionType {
  QR,
  BANK,
  WALLET;

  public static final String QR_TEXT = "QR";
  public static final String BANK_TEXT = "BANK";
  public static final String WALLET_TEXT = "WALLET";


}
