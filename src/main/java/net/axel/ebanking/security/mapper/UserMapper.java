package net.axel.ebanking.security.mapper;

import net.axel.ebanking.mapper.BaseMapper;
import net.axel.ebanking.security.dtos.user.UserRequestDTO;
import net.axel.ebanking.security.dtos.user.UserResponseDTO;
import net.axel.ebanking.security.entities.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<AppUser, UserRequestDTO, UserResponseDTO> {
}
