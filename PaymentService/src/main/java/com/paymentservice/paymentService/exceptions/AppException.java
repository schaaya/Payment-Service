package com.paymentservice.paymentService.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppException extends RuntimeException {

  private final ErrorCode errorCode;

  @Builder
  public AppException(final ErrorCode errorCode,
      final String message,
      final Throwable throwable) {
    super(message, throwable);
    this.errorCode = errorCode;
  }

  public static AppException error(final ErrorCode errorCode,
      final Throwable throwable) {
    return AppException.builder()
        .errorCode(errorCode)
        .throwable(throwable)
        .message(throwable.getLocalizedMessage())
        .build();
  }

  public static AppException propagate(final Throwable e) {
    return propagate(e, ErrorCode.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
  }

  public static AppException propagate(final Throwable e,
      final ErrorCode errorCode) {
    return propagate(e, errorCode, e.getLocalizedMessage());
  }

  public static AppException propagate(final Throwable e,
      final ErrorCode errorCode,
      final String message) {
    final AppException appException = getIfAppExceptionOrNull(e);
    if (appException != null) {
      return appException;
    }

    return AppException.builder()
        .errorCode(errorCode)
        .message(message)
        .throwable(e)
        .build();
  }

  public static AppException getIfAppExceptionOrNull(final Throwable e) {

//    if (e == null) {
//      return null;
//    }

//    if (e instanceof AppException appException) {
//      return appException;
//    }
//
//    //As App Exception is wrapped by DataBuilderException
//    //Only going one level not till root cause as it is an expensive operation
//    if (e.getCause() instanceof AppException appException) {
//      return appException;
//    }

    return null;
  }
}

