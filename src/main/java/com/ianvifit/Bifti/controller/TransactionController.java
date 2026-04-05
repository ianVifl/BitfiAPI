package com.ianvifit.Bifti.controller;

import com.ianvifit.Bifti.model.Transaction;
import com.ianvifit.Bifti.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/bank/transactions")
public class TransactionController {

    public final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/retiro/{idCliente}")
    public Transaction retiro(@PathVariable Long idCliente,@RequestBody Map<String, BigDecimal> payload ){
        BigDecimal amount = payload.get("amount");
        return this.transactionService.procesarRetiro(idCliente, amount );
    }

    @PostMapping("/deposito/{idCliente}")
    public Transaction deposito(@PathVariable Long idCliente, @RequestBody Map<String, BigDecimal> payload ){
        BigDecimal amount = payload.get("amount");
        return this.transactionService.procesarDeposito(idCliente, amount );
    }

    @PostMapping("transferencias/{idCliente}/{idCliente2}")
    public List<Transaction>transferencias(@PathVariable Long idCliente,@PathVariable Long idCliente2,@RequestBody Map<String, BigDecimal> payload ){
        BigDecimal amount = payload.get("amount");
        return this.transactionService.procesarTransferencia(idCliente,idCliente2,amount);
    }
}
