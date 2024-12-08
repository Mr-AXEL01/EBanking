package net.axel.ebanking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")

@RequiredArgsConstructor
public class PublicController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO user = service.createUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/notices")
    public ResponseEntity<String> getNotices() {
        return ResponseEntity.ok("EBank wish you happy new Year 2025.");
    }

    @GetMapping("/contact")
    public ResponseEntity<String> getContact() {
        return ResponseEntity.ok(
                """
                        EBank support at your service:
                        Email: Support@ebank.ma
                        Phone: +212666666666"""
        );
    }
}

