package com.ianvifit.Bifti.service;


import com.ianvifit.Bifti.model.Customer;
import com.ianvifit.Bifti.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //le dice a la memoria que la cargue porque la clase controller la va a necesitar
public class CustomerService {

    //inyeccion de dependencias significa que la clase CustomerService necesita una instancia de CustomerRepository para funcionar
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer registrarCliente(Customer customer){
        return customerRepository.save(customer);
    }


    public List<Customer>listarClientes(){
        return customerRepository.findAll();
    }

}
