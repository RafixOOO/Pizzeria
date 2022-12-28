package Pizza.repos;

import Pizza.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Integer countAllBy();
}