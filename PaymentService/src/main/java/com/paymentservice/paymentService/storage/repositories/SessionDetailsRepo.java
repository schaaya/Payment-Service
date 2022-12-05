package com.paymentservice.paymentService.storage.repositories;

import com.paymentservice.paymentService.storage.SessionDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionDetailsRepo extends JpaRepository<SessionDetails, String> {

}
