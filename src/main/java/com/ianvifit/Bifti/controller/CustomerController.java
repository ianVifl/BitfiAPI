package com.ianvifit.Bifti.controller;


import com.ianvifit.Bifti.model.Customer;
import com.ianvifit.Bifti.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/bank/customers") // Indica la ruta base para todas las peticiones en este controlador
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Guardar cliente
    //Cuando yo hago una peticion POST a /api/customers redirigira a este metodo
    @PostMapping
    public Customer createCustomer (@RequestBody Customer customer) {
        //El @RequestBody convierte el JSON del cuerpo de la peticion en un objeto Customer
        return this.customerService.registrarCliente(customer);
        // y retorna el metodo save del repositorio que guarda el cliente en la base de datos
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        //vas a obtener una lista de objetos Customer y vas a retornar todos los clientes
        return this.customerService.listarClientes();
        //findAll es un metodo del JpaRepository que retorna todos los registros de la tabla Customer
    }


}
