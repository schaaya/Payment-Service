package com.example.WalletService.Controller;

import com.example.WalletService.Model.*;
import com.example.WalletService.Repository.*;
import com.example.WalletService.Util.*;
import com.example.WalletService.exception.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletResource {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    WalletValidator walletValidator = new WalletValidator();
    private static final Logger logger = LoggerFactory.getLogger(WalletResource.class);

    @ApiOperation(value = "Find all the wallet")
    @GetMapping("/findAllWallet")
    List<Wallet> findAllWallet() {
        return walletRepository.findAll();
    }

    // Find a given wallet
    @ApiOperation(value = "Find wallet by Id ")
    @GetMapping("/wallet/{id}")
    Wallet findOneWallet(@ApiParam(value = "Store id of of the point of service to deliver to/collect from", required = true)@PathVariable int id) {
        logger.info("/wallet/{id} called with id "+ id);
        // Optional<User> user = repository.findById(id);
        return walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
    }

    // Save
    @PostMapping("/createNewWallet")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create New Wallet ")
    Wallet CreateNewWallet(@RequestBody Wallet newWallet) {
        if(!walletValidator.validateWalletRequest(newWallet)){
            logger.info("CreateNewWallet request not valid");
            throw  new WalletBadRequest();
        }
        Wallet wallet = walletRepository.save(newWallet);
        return wallet;
    }
    // Save
    @PutMapping("/updateWallet")
    @ApiOperation(value = "Update Wallet ")
    Wallet updateWallet(@RequestBody Wallet newWallet) {
        Wallet wallet = walletRepository.save(newWallet);
        return wallet;
    }
}