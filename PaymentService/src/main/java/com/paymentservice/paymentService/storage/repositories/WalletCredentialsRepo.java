package com.paymentservice.paymentService.storage.repositories;

import com.paymentservice.paymentService.storage.WalletCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletCredentialsRepo extends JpaRepository<WalletCredentials, String> {

}
