package com.ianvifit.Bifti.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor // crea un contructor vacio porque el JPA lo necesita ya que primero crea el objeto y luego lo rellena
@AllArgsConstructor// crea un constructor con todos los atributos, util para pruebas o para crear objetos rapidamente
@Table(name ="accounts")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // generate unique ids in the db automatically
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //lazy trae los datos del cliente solo cuando se necesitan(mejor rendimiento)
    @JoinColumn(name="customer_id" ,nullable = false ) //nombre de la columna en la tabla de cuentas que referencia al cliente
    private Customer customer;

    @Column(unique=true , nullable = false) // unico y no nulo
    private String numeroCuenta;

    private BigDecimal saldo;

    @Column( nullable = false) // no nulo
    private String tipoCuenta;

    @Column( nullable = false) //  no nulo
    private Boolean estadoCuenta;

    @Column( nullable = false) // no nulo
    private  String categoriaCuenta;

    @Column( nullable = false) //  no nulo
    private String limiteTransacciones;

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
