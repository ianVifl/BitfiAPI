package com.ianvifit.Bifti.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // para que se ejecuten las auditorias de createdAt y updatedAt
@Entity
@Table(name ="bank_customers")

public class Customer {

    @CreatedDate
    LocalDateTime createdAt; // fecha de creación CUSTOMER, se llena automáticamente gracias a @CreatedDate y AuditingEntityListener

    @LastModifiedBy
    LocalDateTime modifyedAt;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio") //NotBlank valida a nivel de API
    @Size(min=1, max=50, message = "el nombre debe tener entre 1 y 50 caracteres") //Size valida a nivel de API
    @Column(nullable = false, length = 50, unique = false)//column validad a nivel de BD
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min=1, max=50, message = "el apellido debe tener entre 1 y 50 caracteres")
    @Column(nullable = false, length = 50, unique = false)
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser valido")
    @Column(unique=true, nullable = false)
    private String email;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(min = 10, max = 15, message = "El telefono debe tener entre 10 y 15 caracteres")
    @Column(unique=true, nullable = false)
    private String telefono;

    @Valid // indica que los datos se van a validar dentro de embedded
    @Embedded // indica que es un objeto embebido en la tabla customer es decir que sus atributos se guardan en la misma tabla pero en otra clase dentro de customer (Address)
    private Address direccion;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El CURP es obligatorio")
    @Column(unique=true, nullable = false)
    private String curp;


    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY) /* cascade es para que al elimnar un customer, se eliminen sus acounts relacionados
    y lazy es para que los accounts se carguen solo cuando se necesiten
    **==customer==** es el nombre dela variable que nosotros pusimos en Account para referenciar al customer
    */
    private List<Account> accounts; // listas porque un cliente puede tener varias cuentas y las queremos ver en forma de lista, si solo fuera un dato seria Account account;


}
