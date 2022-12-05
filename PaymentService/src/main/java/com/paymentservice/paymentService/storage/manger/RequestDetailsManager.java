package com.paymentservice.paymentService.storage.manger;

import com.paymentservice.paymentService.storage.RequestDetails;
import com.paymentservice.paymentService.storage.repositories.RequestDetailsRepo;
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
public class RequestDetailsManager {

  @Autowired
  private final RequestDetailsRepo requestDetailsRepo;

  public void validateAndSaveRequestDetails(final String requestId,
      final String clientRequestId,
      final String commandType) {

    final RequestDetails requestDetails = RequestDetails.builder()
        .requestId(requestId)
        .clientRequestId(clientRequestId)
        .commandType(commandType)
        .build();

    requestDetailsRepo.save(requestDetails);
  }

  public RequestDetails getRequestDetails(final String requestId) {
    return requestDetailsRepo.getReferenceById(requestId);
  }

}
