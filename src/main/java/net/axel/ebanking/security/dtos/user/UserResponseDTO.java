package net.axel.ebanking.security.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.axel.ebanking.security.dtos.role.RoleEmbeddedDTO;

public record UserResponseDTO (

        @NotNull Long id,

        @NotBlank String username,

        RoleEmbeddedDTO role
) {
}
