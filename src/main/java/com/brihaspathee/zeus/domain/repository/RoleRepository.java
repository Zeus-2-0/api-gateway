package com.brihaspathee.zeus.domain.repository;

import com.brihaspathee.zeus.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 6:15 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByRoleName(String roleName);
}
