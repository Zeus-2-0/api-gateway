package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.security.model.RoleDto;
import com.brihaspathee.zeus.security.model.RoleList;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, July 2022
 * Time: 12:27 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleService {

    /**
     * Get all the roles in the system
     * @return
     */
    RoleList getAllRoles();

    /**
     * Get role by role id
     * @param roleId
     * @return
     */
    RoleList getRoleById(UUID roleId);

    /**
     * Get role by role name
     * @param roleName
     * @return
     */
    RoleList getRoleByRoleName(String roleName);

    /**
     * Save the role
     * Create the role if not present
     * Update the role if present
     * @param roleDto
     * @return
     */
    RoleDto saveRole(RoleDto roleDto);
}
