package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.security.model.RoleDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 1:33 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleMapper {

    /**
     * Converts the Role Dto to Role entity
     * @param roleDto
     * @return
     */
    Role roleDtoToRole(RoleDto roleDto);

    /**
     * Converts the role entity to role dto
     * @param role
     * @return
     */
    RoleDto roleToRoleDto(Role role);

    /**
     * Converts the set of role dtos to role entities
     * @param roleDtos
     * @return
     */
    List<Role> roleDtosToRoles(List<RoleDto> roleDtos);

    /**
     * Converts list of role entities to role dtos
     * @param roles
     * @return
     */
    List<RoleDto> rolesToRoleDtos(List<Role> roles);
}
