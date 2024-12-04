package net.axel.ebanking.security.mapper;

import net.axel.ebanking.mapper.BaseMapper;
import net.axel.ebanking.security.dtos.role.RoleRequestDTO;
import net.axel.ebanking.security.dtos.role.RoleResponseDTO;
import net.axel.ebanking.security.entities.AppRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<AppRole, RoleRequestDTO, RoleResponseDTO> {
}
