package net.axel.ebanking.security.dtos.role;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(
        @NotBlank String name
) {
}
