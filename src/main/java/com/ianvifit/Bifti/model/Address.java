package com.ianvifit.Bifti.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String calle;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
}
