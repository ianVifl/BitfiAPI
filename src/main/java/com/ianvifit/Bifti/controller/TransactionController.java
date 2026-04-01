package com.ianvifit.Bifti.controller;


import com.ianvifit.Bifti.model.Customer;
import com.ianvifit.Bifti.service.TransactionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController("/bank/transactions")
public class TransactionController {

    public final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/retiro")
    public void crearTransaction(@RequestBody Customer customer ,@PathVariable BigDecimal amount ){
        transactionService.

    }
}
