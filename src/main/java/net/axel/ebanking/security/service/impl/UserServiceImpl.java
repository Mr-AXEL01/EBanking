package net.axel.ebanking.security.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.ebanking.exception.domain.BadCredentialsException;
import net.axel.ebanking.exception.domain.BusinessException;
import net.axel.ebanking.exception.domain.ResourceNotFoundException;
import net.axel.ebanking.exception.domain.UsernameAlreadyExistsException;
import net.axel.ebanking.security.dtos.role.RoleRequestDTO;
import net.axel.ebanking.security.dtos.role.RoleResponseDTO;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.dtos.user.UserUpdatePasswordDTO;
import net.axel.ebanking.security.entities.AppRole;
import net.axel.ebanking.security.entities.AppUser;
import net.axel.ebanking.security.mapper.RoleMapper;
import net.axel.ebanking.security.mapper.UserMapper;
import net.axel.ebanking.security.repository.RoleRepository;
import net.axel.ebanking.security.repository.UserRepository;
import net.axel.ebanking.security.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (!dto.passwordConfirmation().equals(dto.password())) {
            throw new BadCredentialsException("Passwords do not match.");
        }

        if (repository.existsByUsername(dto.username())) {
            throw new UsernameAlreadyExistsException(dto.username());
        }

        AppRole role = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Need Role in DB."));

        AppUser user = mapper.toEntity(dto)
                .setPassword(passwordEncoder.encode(dto.password()))
                .setRole(role);

        AppUser savedUser = repository.save(user);

        return mapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO findUser(String username) {
        return repository.findByUsername(username)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("No User found with the given username" + username));
    }

    public AppUser findUserEntity(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No User found with the given username" + username));
    }

    @Override
    public UserResponseDTO updateUserRole(String username, RoleRequestDTO dto) {
        AppRole role = roleRepository.findByName(dto.name())
                .orElseThrow(() -> new ResourceNotFoundException("No Role found with the given role : " + dto.name()));

        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No User found with the given username : " + username));

        if (user.getRole().equals(role)) {
            throw new BusinessException("Role already assigned!");
        }

        return mapper.toResponseDto(
                user.setRole(role)
        );
    }

    @Override
    public void deleteUser(String username) {
        if (!repository.existsByUsername(username)) {
            throw new ResourceNotFoundException("Non exists User.");
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
    public UserResponseDTO updatePassword(UserUpdatePasswordDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with the username provided :" + username));

        if (!passwordEncoder.matches(dto.currentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect.");
        }

        if (passwordEncoder.matches(dto.newPassword(), user.getPassword())) {
            throw new BusinessException("The new password can't be the same as the current one.");
        }

        user.setPassword(passwordEncoder.encode(dto.newPassword()));

        return mapper.toResponseDto(user);
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
