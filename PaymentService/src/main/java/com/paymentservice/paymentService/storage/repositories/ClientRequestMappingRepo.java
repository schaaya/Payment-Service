package com.paymentservice.paymentService.storage.repositories;

import com.paymentservice.paymentService.storage.ClientRequestMapping;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRequestMappingRepo extends JpaRepository<ClientRequestMapping, String> {

}
