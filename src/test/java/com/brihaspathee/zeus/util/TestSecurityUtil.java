package com.brihaspathee.zeus.util;

import com.brihaspathee.zeus.security.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, August 2022
 * Time: 7:33 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.util
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class TestSecurityUtil {

    /**
     * Compare the expected user list and the actual user list
     * @param expectedUserList
     * @param actualUserList
     */
    public static void assertUserList(UserList expectedUserList, UserList actualUserList, boolean actualPasswordEncrypted){
        List<UserDto> expectedUserDtos = expectedUserList.getUserDtos();
        List<UserDto> actualUserDtos = actualUserList.getUserDtos();
        log.info("Ex Users:{}", expectedUserDtos);
        log.info("Ac Users:{}", actualUserDtos);
        expectedUserDtos.stream().forEach( (expectedUserDto -> {
            UserDto actualUser = actualUserDtos.stream().filter(
                            (actualUserDto) -> {
                                return expectedUserDto.getUserId().equals(actualUserDto.getUserId());
                            })
                    .findFirst().orElse(UserDto.builder()
                            .userId(UUID.randomUUID())
                            .username("Random User")
                            .build());
            assertUserDetails(expectedUserDto, actualUser, actualPasswordEncrypted);
        }));
    }

    /**
     * Compare details of the expected user and actual user
     * @param expectedUser
     * @param actualUser
     */
    public static void assertUserDetails(UserDto expectedUser, UserDto actualUser, boolean actualPasswordEncrypted){
        log.info("Expected User:{}", expectedUser);
        log.info("Actual User:{}", actualUser);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        if(actualPasswordEncrypted){
            String actualPassword = actualUser.getPassword().replace("{bcrypt}", "");
            log.info("Actual password:{}", actualPassword);
            log.info("Expected password:{}", expectedUser.getPassword());
            assertTrue(bcrypt.matches(expectedUser.getPassword(), actualPassword));
        }else{
            log.info("Actual password:{}", actualUser.getPassword());
            log.info("Expected password:{}", expectedUser.getPassword());
            assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        }
        assertRoleList(expectedUser.getRoleDtos(), actualUser.getRoleDtos());
    }

    /**
     * Compare the expected role list and the actual role list
     * @param expectedRoles
     * @param actualRoles
     */
    public static void assertRoleList(List<RoleDto> expectedRoles, List<RoleDto> actualRoles){
        expectedRoles.stream().forEach( (expectedRoleDto -> {
            RoleDto actualRole = actualRoles.stream().filter(
                            (actualRoleDto) -> {
                                return expectedRoleDto.getRoleId().equals(actualRoleDto.getRoleId());
                            })
                    .findFirst().orElse(RoleDto.builder()
                            .roleId(UUID.randomUUID())
                            .roleName("Random Role")
                            .build());
            assertRoleDetails(expectedRoleDto, actualRole);
        }));
    }

    /**
     * Compare details of the expected role and actual role
     * @param expectedRoleDto
     * @param actualRoleDto
     */
    public static void assertRoleDetails(RoleDto expectedRoleDto, RoleDto actualRoleDto){
        assertEquals(expectedRoleDto.getRoleId(), actualRoleDto.getRoleId());
        assertEquals(expectedRoleDto.getRoleName(), actualRoleDto.getRoleName());
        assertAuthorityList(AuthorityList.builder().authorityDtos(expectedRoleDto.getAuthorityDtos()).build(),
                AuthorityList.builder().authorityDtos(actualRoleDto.getAuthorityDtos()).build());
    }

    /**
     * Check if all authorities are present
     * @param expectedAuthorityList
     * @param actualAuthorityList
     */
    public static void assertAuthorityList(AuthorityList expectedAuthorityList, AuthorityList actualAuthorityList){
        List<AuthorityDto> expectedAuthorityDtos = expectedAuthorityList.getAuthorityDtos();
        List<AuthorityDto> actualAuthorityDtos = actualAuthorityList.getAuthorityDtos();
        log.info("Ex Authorities:{}", expectedAuthorityDtos);
        log.info("Ac Authorities:{}", actualAuthorityDtos);
        expectedAuthorityDtos.stream().forEach( (expectedAuthorityDto -> {
            AuthorityDto actualAuthority = actualAuthorityDtos.stream().filter(
                            (actualAuthorityDto) -> {
                                return expectedAuthorityDto.getAuthorityId().equals(actualAuthorityDto.getAuthorityId());
                            })
                    .findFirst().orElse(AuthorityDto.builder()
                            .authorityId(UUID.randomUUID())
                            .permission("Random Authority")
                            .build());
            assertAuthorityDetails(expectedAuthorityDto, actualAuthority);
        }));
    }

    /**
     * Compare the authority details
     * @param expectedAuthorityDto
     * @param actualAuthority
     */
    public static void assertAuthorityDetails(AuthorityDto expectedAuthorityDto, AuthorityDto actualAuthority){
        log.info("Expected Authority:{}", expectedAuthorityDto);
        log.info("Actual Authority:{}", actualAuthority);
        assertEquals(expectedAuthorityDto.getAuthorityId(), actualAuthority.getAuthorityId());
        assertEquals(expectedAuthorityDto.getPermission(), actualAuthority.getPermission());
    }
}
