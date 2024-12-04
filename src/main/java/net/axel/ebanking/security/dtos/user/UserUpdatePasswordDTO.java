package net.axel.ebanking.security.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdatePasswordDTO(
        @NotBlank String currentPassword,

        @NotBlank String newPassword
) {
}
