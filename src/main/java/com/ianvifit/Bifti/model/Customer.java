package com.ianvifit.Bifti.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="bank_customers")

public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Size(min=1, max=50, message = "el nombre debe tener entre 1 y 50 caracteres") //Size valida a nivel de API
    @Column(nullable = false, length = 50)//column validad a nivel de BD
    private String nombre;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(unique=true, nullable = false)
    private String telefono;

    @Embedded // indica que es un objeto embebido en la tabla customer es decir que sus atributos se guardan en la misma tabla pero en otra clase dentro de customer (Address)
    private Address direccion;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(unique=true, nullable = false)
    private String curp;


    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY) /* cascade es para que al elimnar un customer, se eliminen sus acounts relacionados
    y lazy es para que los accounts se carguen solo cuando se necesiten
    **==customer==** es el nombre dela variable que nosotros pusimos en Account para referenciar al customer
    */
    private List<Account> accounts; // listas porque un cliente puede tener varias cuentas y las queremos ver en forma de lista, si solo fuera un dato seria Account account;


}
