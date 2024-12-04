package net.axel.ebanking.security.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.ebanking.security.dtos.role.RoleRequestDTO;
import net.axel.ebanking.security.dtos.role.RoleResponseDTO;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.entities.AppRole;
import net.axel.ebanking.security.entities.AppUser;
import net.axel.ebanking.security.mapper.RoleMapper;
import net.axel.ebanking.security.mapper.UserMapper;
import net.axel.ebanking.security.repository.RoleRepository;
import net.axel.ebanking.security.repository.UserRepository;
import net.axel.ebanking.security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        AppUser user = mapper.toEntity(dto)
                .password(passwordEncoder.encode(
                        dto.password()
                ));
        
        AppUser savedUser = repository.save(user);

        return mapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO findUser(String username) {
        return repository.findByUsername(username)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("No User found with the given username" + username));
    }

    @Override
    public UserResponseDTO updateUserRole(String username, RoleRequestDTO dto) {
        AppRole role = roleRepository.findByName(dto.name())
                .orElseThrow(() -> new RuntimeException("No User found with the given username" + username));

        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No User found with the given username" + username));

        if (user.role().equals(role)) {
            throw new RuntimeException("Role already assigned!");
        }

        return mapper.toResponseDto(
                user.role(role)
        );
    }

    @Override
    public void deleteUser(String username) {
        if (!repository.existsByUsername(username)) {
            throw new RuntimeException("Non exists User.");
        }

        UserResponseDTO user = findUser(username);

        repository.deleteById(user.id());
    }

    @Override
    public List<UserResponseDTO> findUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO dto) {
        Optional<AppRole> role = roleRepository.findByName(dto.name());
        if (role.isPresent()) {
            throw new RuntimeException("Role already exists");
        }
        AppRole savedRole = roleRepository.save(roleMapper.toEntity(dto));
        return roleMapper.toResponseDto(savedRole);
    }
}
