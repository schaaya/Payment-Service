package com.paymentservice.paymentService.storage.repositories;

import com.paymentservice.paymentService.storage.WalletDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletDetailsRepo extends JpaRepository<WalletDetails, String> {

}
