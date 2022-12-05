package com.paymentservice.paymentService.storage.manger;

import com.paymentservice.paymentService.exceptions.AppException;
import com.paymentservice.paymentService.exceptions.ErrorCode;
import com.paymentservice.paymentService.storage.ClientRequestMapping;
import com.paymentservice.paymentService.storage.repositories.ClientRequestMappingRepo;
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
@Service
public class ClientRequestMappingManager {

  @Autowired
  private ClientRequestMappingRepo clientRequestMappingRepo;


  public void saveClientRequestMapping(final String clientRequestId,
      final String requestId) {
    final ClientRequestMapping clientRequestMapping = ClientRequestMapping.builder()
        .clientRequestId(clientRequestId)
        .requestId(requestId)
        .build();

    if (!clientRequestMappingRepo.existsById(clientRequestId)) {
      clientRequestMappingRepo.save(clientRequestMapping);
    } else {
      throw AppException.builder()
          .message("Send a new request with different client request ID")
          .errorCode(ErrorCode.INVALID_REQUEST)
          .build();
    }
  }

}
