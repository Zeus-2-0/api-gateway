package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.zeus.mapper.interfaces.RoleMapper;
import com.brihaspathee.zeus.web.model.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 2:05 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {

    /**
     * The authority mapper to map the authorities that are associated with the role
     */
    private final AuthorityMapper authorityMapper;

    /**
     * Converts the Role Dto to Role entity
     * @param roleDto
     * @return
     */
    @Override
    public Role roleDtoToRole(RoleDto roleDto) {
        log.info("Received roleDto:{}", roleDto);
        if(roleDto == null){
            return null;
        }
        Role role = Role.builder()
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        log.info("Mapped role in the middle:{}", role);
        if(roleDto.getAuthorityDtos() != null && !roleDto.getAuthorityDtos().isEmpty()){
            role.setAuthorities(authorityMapper.
                    authorityDtosToAuthorities(
                            roleDto.getAuthorityDtos())
                    .stream().collect(Collectors.toSet()));
        }
        log.info("Mapped role:{}", role);
        return role;
    }

    /**
     * Converts the role entity to role dto
     * @param role
     * @return
     */
    @Override
    public RoleDto roleToRoleDto(Role role) {
        if(role == null){
            return null;
        }
        RoleDto roleDto = RoleDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
        if(role.getAuthorities() != null && !role.getAuthorities().isEmpty()){
            roleDto.setAuthorityDtos(
                    authorityMapper.authoritiesToAuthorityDtos(
                            role.getAuthorities().stream().collect(Collectors.toList())));
        }
        return roleDto;
    }

    /**
     * Converts the set of role dtos to role entities
     * @param roleDtos
     * @return
     */
    @Override
    public List<Role> roleDtosToRoles(List<RoleDto> roleDtos) {

        return roleDtos.stream().map(this::roleDtoToRole).collect(Collectors.toList());
    }

    /**
     * Converts list of role entities to role dtos
     * @param roles
     * @return
     */
    @Override
    public List<RoleDto> rolesToRoleDtos(List<Role> roles) {

        return roles.stream().map(this::roleToRoleDto).collect(Collectors.toList());
    }
}
