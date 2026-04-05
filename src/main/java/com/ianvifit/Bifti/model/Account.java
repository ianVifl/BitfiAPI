package com.ianvifit.Bifti.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor // crea un contructor vacio porque el JPA lo necesita ya que primero crea el objeto y luego lo rellena
@AllArgsConstructor// crea un constructor con todos los atributos, util para pruebas o para crear objetos rapidamente
@Table(name ="accounts")
@EntityListeners(AuditingEntityListener.class) // para que se ejecuten las auditorias de createdAt y updatedAt FECHAS Y HORAS
@Entity
public class Account {

    @CreatedDate
    LocalDateTime createdAt; // fecha de creación de la cuenta, se llena automáticamente gracias a @CreatedDate y AuditingEntityListener

    @LastModifiedBy
    LocalDateTime modifyedAt; // fecha de última modificación de la cuenta, se llena automáticamente gracias a AuditingEntityListener


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // generate unique ids in the db automatically
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //lazy trae los datos del cliente solo cuando se necesitan(mejor rendimiento)
    @JoinColumn(name="customer_id" ,nullable = false ) //nombre de la columna en la tabla de cuentas que referencia al cliente
    @JsonIgnore
    private Customer customer;


    @NotBlank(message = "El numero de cueta es obligatorio")
    @Size(min =10,max = 20)
    @Pattern(regexp = "^[0-9]+$", message = "El numero de cuenta solo puede contener numeros")
    @Column(unique=true , nullable = false) // unico y no nulo
    private String numeroCuenta;


    @NotNull(message = "El saldo no puede ser nulo ")
    @DecimalMin(value = "0.0", inclusive = false , message = "El saldo debe ser positivo")
    private BigDecimal saldo;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Column( nullable = false) // no nulo
    private String tipoCuenta;

    @NotNull(message = "El estado de la cuenta no puede ser nulo")
    @Column( nullable = false) //  no nulo
    private Boolean estadoCuenta;

    @NotBlank(message = "La categoria de la cuenta es obligatoria")
    @Column( nullable = false) // no nulo
    private  String categoriaCuenta;

    @NotBlank(message= "El limite de transacciones es obligatorio")
    @Column( nullable = false) //  no nulo
    private String limiteTransacciones;

    @NotNull(message = "La fecha de registro no puede ser nula")
    @Column( nullable = false) //no nulo
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy="account", fetch =FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Transaction> transacciones;
    // --- RELACIÓN CON TRANSACCIONES (LADO VIRTUAL) ---
    // OneToMany: Una Cuenta tiene MUCHAS Transacciones.
    // Esta lista es VIRTUAL. No crea columna en la tabla 'accounts'.
    // mappedBy = "account": ¡OJO AQUÍ! "Ve a la clase Transaction y busca la variable llamada 'account'
    // para saber cuáles son mías". NO busques columnas SQL, busca variables Java.
    // cascade = CascadeType.ALL: Si borro la Cuenta, se borran sus transacciones (opcional pero útil).


}
