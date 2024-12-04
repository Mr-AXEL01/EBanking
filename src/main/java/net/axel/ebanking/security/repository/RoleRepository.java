package net.axel.ebanking.security.repository;

import net.axel.ebanking.security.entities.AppRole;
import net.axel.ebanking.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<AppRole, Long> {

    Optional<AppRole> findByName(String name);
}
