package com.paymentservice.paymentService.utils;

import java.util.UUID;
import org.springframework.context.annotation.Bean;

//private class data pattern
public final class RequestUtils {

  private RequestUtils() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  @Bean
  private static String generateRandomId(final String prefix) {
    return prefix + UUID.randomUUID();
  }

  @Bean
  public static String generateRequestId() {
    return generateRandomId("REQ");
  }

  @Bean
  public static String generateTransactionId() {
    return generateRandomId("TID");
  }

  @Bean
  public static String generateSessionId() {
    return generateRandomId("SID");
  }

}
