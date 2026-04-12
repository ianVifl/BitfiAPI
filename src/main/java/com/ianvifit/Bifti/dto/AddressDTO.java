package com.ianvifit.Bifti.dto;

import com.ianvifit.Bifti.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotBlank(message = "La calle es obligatoria")
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El código postal es obligatorio")
    @Size(min = 5, max = 10, message = "El código postal debe tener entre 5 y 10 caracteres")
    private String codigoPostal;

    @NotBlank(message = "El país es obligatorio")
    private String pais;

    public @Valid Address toEntity() {
        Address address = new Address();
        address.setCalle(this.calle);
        address.setCiudad(this.ciudad);
        address.setEstado(this.estado);
        address.setCodigoPostal(this.codigoPostal);
        address.setPais(this.pais);
        return address;
    }
}