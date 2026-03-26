package com.ianvifit.Bifti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    @NotBlank(message = "La calle es obligatoria")
    @Column(nullable = false, length = 100, unique = false)
    private String calle;
    @NotBlank(message = "La ciudad es obligatoria")
    @Column(nullable = false, length = 50,unique = false)
    private String ciudad;

    @NotBlank(message = "La estado es obligatoria")
    @Column(nullable = false, length = 50, unique = false)
    private String estado;

    @NotBlank(message = "El codigo postal es obligatorio")
    @Size(min = 5, max = 10, message = "El codigo postal debe tener entre 5 y 10 caracteres")
    @Column(nullable = false, length = 10, unique = false)
    private String codigoPostal;

    @NotBlank(message = "La pais es obligatoria")
    @Column(nullable = false,length = 50, unique = false)
    private String pais;
}
