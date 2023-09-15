package pcc.portal.portalback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pcc.portal.portalback.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
