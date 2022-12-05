package com.paymentservice.paymentService.storage.repositories;

import com.paymentservice.paymentService.storage.RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDetailsRepo extends JpaRepository<RequestDetails, String> {

}
