package com.paymentservice.paymentService.storage.manger;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.storage.SessionDetails;
import com.paymentservice.paymentService.storage.repositories.SessionDetailsRepo;
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
public class SessionDetailsManager {

  @Autowired
  private SessionDetailsRepo sessionDetailsRepo;

  public SessionDetails validateSessionIdAndGetSessionDetails(final String sessionId) {
    if (sessionDetailsRepo.existsById(sessionId)) {
      return sessionDetailsRepo.getReferenceById(sessionId);
    } else {
      throw new AppException(ErrorCode.SESSION_DOES_NOT_EXIST, "Session Does Not Exists", null);
    }
  }

  public String getPhoneNumberFromSessionId(final String sessionId) {
    final var sessionDetails = validateSessionIdAndGetSessionDetails(sessionId);

    return sessionDetails.getPhoneNumber();
  }

  public void saveSessionDetails(final String sessionId,
      final String requestId,
      final String phoneNumber) {

    final SessionDetails sessionDetails = SessionDetails.builder()
        .sessionId(sessionId)
        .requestId(requestId)
        .phoneNumber(phoneNumber)
        .build();

    sessionDetailsRepo.save(sessionDetails);
  }

  public SessionDetails getSessionDetails(final String sessionId) {
    return sessionDetailsRepo.getReferenceById(sessionId);
  }

  public void deleteSession(final String sessionId) {

    if (!sessionDetailsRepo.existsById(sessionId)) {
      sessionDetailsRepo.delete(sessionDetailsRepo.getReferenceById(sessionId));
    }
  }


}
