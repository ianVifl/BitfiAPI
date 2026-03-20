package com.ianvifit.Bifti.service;


import com.ianvifit.Bifti.model.Account;
import com.ianvifit.Bifti.model.Customer;
import com.ianvifit.Bifti.repository.AccountRepository;
import com.ianvifit.Bifti.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    // instanciamos dos repositorios porque para crear una cuenta necesitamos acceder a:
    // 1.- al repositorio de cuentas para guardar la cuenta
    // 2.- al repositorio de clientes para verificar que el cliente existe antes de crear la cuenta
    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }


    public Account crearCuenta(Long idCliente, Account nuevaCuenta){
        //buscamos el cliente por su id y lo guardamos en un Optional para manejar el caso de que no exista
        // el Optional es una clase contenedora que puede contener un valor o estar vacía
        Optional<Customer> clienteOpt= customerRepository.findById(idCliente);
        if (clienteOpt.isEmpty()){
           throw new RuntimeException("El cliente con id "+ idCliente+ " no se encontro");
        }
        //si no esta vacia , obtenemos el cliente que ya sabemos que SI existe
        Customer cliente = clienteOpt.get();
        //en esta línea cliente ya es un objeto CustomeRlo cuál se asignó el contenido del Optional que ya sabemos
        //que era un cliente existente y por tánto un objeto válido que podemos manejar


        //asignamos el cliente a la nueva cuenta
        nuevaCuenta.setCustomer(cliente);
        //guardamos tambine la fecha de registro de la cuenta como la fecha actual
        nuevaCuenta.setFechaRegistro(java.time.LocalDate.now());


        //guaradamos la nueva cuenta en su tabla y la retornamos para poder utilizarla en el controlador o en otro lado
        return accountRepository.save(nuevaCuenta);


    }



}
