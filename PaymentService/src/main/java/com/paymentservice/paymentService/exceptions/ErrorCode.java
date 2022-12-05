package com.paymentservice.paymentService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCode {

  INTERNAL_SERVER_ERROR(500),
  DAO_ERROR(500),
  IO_ERROR(500),
  UNSUPPORTED_OPERATION(404),
  COMMUNICATION_ERROR(500),
  JSON_ERROR(500),
  DOCUMENT_NOT_VERIFIED(500),
  CLIENT_TIMEOUT(500),
  USER_DOES_NOT_EXIST(500),
  USER_UNAUTHORIZED(500),
  INVALID_REQUEST(500),
  SESSION_DOES_NOT_EXIST(500),
  WALLET_DOES_NOT_EXIST(500),
  LOW_BALANCE(206);

  private final int httpStatusCode;
}
