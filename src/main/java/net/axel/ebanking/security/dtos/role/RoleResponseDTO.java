package net.axel.ebanking.security.dtos.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleResponseDTO(

        @NotNull Long id,

        @NotBlank String name
) {
}
