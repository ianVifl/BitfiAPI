package com.ianvifit.Bifti.service;


import com.ianvifit.Bifti.dto.CustomerUpdateDTO;
import com.ianvifit.Bifti.model.Customer;
import com.ianvifit.Bifti.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //Modificar clientes
    public Customer actualizarCliente(Long idCliente, CustomerUpdateDTO dto) {
        Optional<Customer> clienteBuscado = customerRepository.findById(idCliente);
        if (clienteBuscado.isEmpty()) {
            throw new IllegalArgumentException("El cliente buscado no se encontro");
        }

        Customer customer = clienteBuscado.get();

        if (dto.getNombre() != null && !dto.getNombre().isEmpty()) {
            customer.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null && !dto.getApellido().isEmpty()) {
            customer.setApellido(dto.getApellido());
        }
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            customer.setEmail(dto.getEmail());
        }
        if (dto.getTelefono() != null && !dto.getTelefono().isEmpty()) {
            customer.setTelefono(dto.getTelefono());
        }
        //no se agrega el isEmpty porque la fecha de nacimiento es un LocalDate
        //y no un String, entonces no se puede validar con isEmpty, solo se valida que no sea null
        if (dto.getFechaNacimiento() != null) {
            customer.setFechaNacimiento(dto.getFechaNacimiento());
        }
        //se agrega un ".toEntity()" ya que se esta tratando de un atributo embebido,
        // entonces se necesita convertir el DTO a una entidad para poder asignarlo al cliente
        if (dto.getDireccion() != null) {
            customer.setDireccion(dto.getDireccion().toEntity());
        }
        if(dto.getCurp() != null && !dto.getCurp().isEmpty()) {
            customer.setCurp(dto.getCurp());
        }


        return customerRepository.save(customer);
    }

}
