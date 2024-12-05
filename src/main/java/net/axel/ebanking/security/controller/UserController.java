package net.axel.ebanking.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.dtos.user.UserUpdatePasswordDTO;
import net.axel.ebanking.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class UserController {

    public final static String CONTROLLER_PATH = "/api/user/users";

    private final UserService service;

    @PutMapping
    public ResponseEntity<UserResponseDTO> updatePassword(@RequestBody @Valid UserUpdatePasswordDTO dto) {
        UserResponseDTO user = service.updatePassword(dto);
        return ResponseEntity.ok(user);
    }
}
