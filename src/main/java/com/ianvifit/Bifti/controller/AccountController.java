package com.ianvifit.Bifti.controller;

import com.ianvifit.Bifti.model.Account;
import com.ianvifit.Bifti.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/accounts")
public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{idCliente}")
    // el idCliente viene en la ruta de la peticion y lo ponemos para que
    // sepa a que cliente se le va a crear la cuenta esta varaiable se pasa a el metodo para el parametro Long idCliente

    public Account crearCuenta (@RequestBody Account account,@PathVariable Long idCliente) {
        //el @PathVariable Long idCliente indica que el valor de idCliente se obtiene de la ruta de la peticion,
        //es decir, se extrae el valor de idCliente de la URL y se asigna a la variable idCliente del metodo crearCuenta
        return accountService.crearCuenta(idCliente,account);
    }

    @GetMapping("/{idCliente}")
    public Account verCuenta(@PathVariable Long idCliente){
        return accountService.verCuenta(idCliente);
    }
}
