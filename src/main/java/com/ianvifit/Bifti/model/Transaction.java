package com.ianvifit.Bifti.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transactions")

public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Column(nullable = false)// no nulo
    @Enumerated(EnumType.STRING) // Guarda el TEXTO de l enum ("DEPOSITO")
    private TransactionType tipoDeTransaccion;

    private LocalDate fechaTransaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable = false)//en name ponemos el nombre de la columna en la account que referencia a la transaccion
    private Account account;
    // --- RELACIÓN (LADO DUEÑO) ---
    // Esta clase es la "Dueña" porque TIENE la columna física (FK).
    // @JoinColumn: "Oye BD, crea una columna llamada 'account_id' aquí para poder referenciarla como una FK."


}
