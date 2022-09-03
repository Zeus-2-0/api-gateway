package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.repository.AuthorityRepository;
import com.brihaspathee.zeus.domain.repository.RoleRepository;
import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.exception.AuthorityNotFoundException;
import com.brihaspathee.zeus.exception.RoleNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.RoleMapper;
import com.brihaspathee.zeus.security.model.RoleDto;
import com.brihaspathee.zeus.security.model.RoleList;
import com.brihaspathee.zeus.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, July 2022
 * Time: 12:33 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    /**
     * The repository to perform CRUD operations
     */
    private final RoleRepository roleRepository;

    /**
     * The authority repository to check if the authorities associated with role is present
     */
    private final AuthorityRepository authorityRepository;

    /**
     * The mapper to convert the entity to dto and vice versa
     */
    private final RoleMapper roleMapper;


    /**
     * Get all the roles in the system
     * @return
     */
    @Override
    public RoleList getAllRoles() {
        return RoleList.builder()
                .roleDtos(roleMapper.rolesToRoleDtos(roleRepository.findAll()))
                .build();
    }

    /**
     * Get role by role id
     * @param roleId
     * @return
     */
    @Override
    public RoleList getRoleById(UUID roleId) {
        RoleDto roleDto = roleMapper.roleToRoleDto(roleRepository.findById(roleId).orElseThrow( () -> {
            throw new RoleNotFoundException("Role with role id " + roleId + " not found" );
        }));
        return RoleList.builder()
                .roleDtos(Arrays.asList(roleDto))
                .build();
    }

    /**
     * Get role by role name
     * @param roleName
     * @return
     */
    @Override
    public RoleList getRoleByRoleName(String roleName) {
        RoleDto roleDto = roleMapper.roleToRoleDto(roleRepository.findRoleByRoleName(roleName).orElseThrow( () -> {
            throw new RoleNotFoundException("Role with role name " + roleName + " not found" );
        }));
        return RoleList.builder()
                .roleDtos(Arrays.asList(roleDto))
                .build();
    }

    /**
     * Save the role
     * Create the role if not present
     * Update the role if present
     * @param roleDto
     * @return
     */
    @Override
    public RoleDto saveRole(RoleDto roleDto) {

        boolean authoritiesPresent = roleDto.getAuthorityDtos().stream().allMatch(authorityDto -> {
            if(authorityDto.getAuthorityId() != null){
                return authorityRepository.existsById(authorityDto.getAuthorityId());
            }
            Optional<Authority> authority =  authorityRepository.findAuthorityByPermission(authorityDto.getPermission());

            if(authority.isEmpty()){
                return false;
            }else{
                authorityDto.setAuthorityId(authority.get().getAuthorityId());
                return true;
            }
        });
        if(!authoritiesPresent){
            throw new AuthorityNotFoundException("One or more authorities not found");
        }
        log.info("Role dto receive:{}", roleDto);
        Role role = roleMapper.roleDtoToRole(roleDto);
        log.info("Role to be updated:{}", role);
        role = roleRepository.save(role);

        return roleMapper.roleToRoleDto(role);
    }
}
