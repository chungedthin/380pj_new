package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
