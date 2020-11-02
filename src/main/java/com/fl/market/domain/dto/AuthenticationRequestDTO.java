package com.fl.market.domain.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequestDTO {

    @NotNull(message = "Campo requerido")
    @NotEmpty(message = "Campo requerido")
    @Email(message = "Debe ingresar un email valido")
    private String username;

    @NotNull(message = "Campo requerido")
    @NotEmpty(message = "Campo requerido")
    private String password;

}
