package net.axel.ebanking.security.service;

import net.axel.ebanking.security.dtos.role.RoleRequestDTO;
import net.axel.ebanking.security.dtos.role.RoleResponseDTO;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.dtos.user.UserUpdatePasswordDTO;

import java.util.List;

// TODO : the update of user password

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO findUser(String username);

    UserResponseDTO updateUserRole(String username, RoleRequestDTO dto);

    void deleteUser(String username);

    List<UserResponseDTO> findUsers();

    UserResponseDTO updatePassword(UserUpdatePasswordDTO dto);

    RoleResponseDTO createRole(RoleRequestDTO dto);
}
