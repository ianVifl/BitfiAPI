package com.ianvifit.Bifti.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto no puede ser nulo")
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull(message = "El tipo de transacción no puede ser nulo")
    @Column(nullable = false)// no nulo
    @Enumerated(EnumType.STRING) // Guarda el TEXTO del enum ("DEPOSITO")
    private TransactionType tipoDeTransaccion;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime fechaTransaccion;

    @LastModifiedBy
    LocalDateTime modifyedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable = false)//en name ponemos el nombre de la columna en la account que referencia a la transaccion
    @JsonIgnore
    private Account account;
    // --- RELACIÓN (LADO DUEÑO) ---
    // Esta clase es la "Dueña" porque TIENE la columna física (FK).
    // @JoinColumn: "Oye BD, crea una columna llamada 'account_id' aquí para poder referenciarla como una FK."


}
