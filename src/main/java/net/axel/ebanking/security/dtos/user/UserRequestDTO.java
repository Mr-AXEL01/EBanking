package net.axel.ebanking.security.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank String username,

        @NotBlank String password
) {
}
