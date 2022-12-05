package com.paymentservice.paymentService.services;

import com.paymentservice.paymentService.models.OperationType;
import com.paymentservice.paymentService.payloads.common.BankDetails;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Configuration
@ComponentScan
@Service
public class BankingService {


  public void validateAndDoOperation(final BankDetails bankDetails,
      final Integer amount,
      final OperationType operationType) {

    //validate bank and do operation
  }
}
