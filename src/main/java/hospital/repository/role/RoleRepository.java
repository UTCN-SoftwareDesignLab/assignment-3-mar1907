package hospital.repository.role;

import hospital.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer>{
    void deleteByUsername(String username);
    Role findRoleByUsername(String username);
}
