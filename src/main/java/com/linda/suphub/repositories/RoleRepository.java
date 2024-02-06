package com.linda.suphub.repositories;

import com.linda.suphub.models.Post;
import com.linda.suphub.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);


}
