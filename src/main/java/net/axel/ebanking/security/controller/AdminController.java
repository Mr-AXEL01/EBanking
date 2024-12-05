package net.axel.ebanking.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.ebanking.security.dtos.role.RoleRequestDTO;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AdminController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class AdminController {

    public final static String CONTROLLER_PATH = "/api/admin/users";

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = service.findUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable("username") String username) {
        UserResponseDTO user = service.findUser(username);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        service.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/updateRole")
    public ResponseEntity<UserResponseDTO> updateRole(@PathVariable("username") String username,
                                                      @RequestBody @Valid RoleRequestDTO dto) {
        UserResponseDTO updatedUser = service.updateUserRole(username, dto);
        return ResponseEntity.ok(updatedUser);
    }
}
