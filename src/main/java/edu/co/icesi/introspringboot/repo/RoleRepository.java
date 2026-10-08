package edu.co.icesi.introspringboot.repo;

import edu.co.icesi.introspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByNameContainingIgnoreCase(String name);
    List<Role> findByUserRoles_User_Username(String username);
}
