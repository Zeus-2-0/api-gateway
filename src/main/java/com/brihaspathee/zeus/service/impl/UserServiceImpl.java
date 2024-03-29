package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.repository.RoleRepository;
import com.brihaspathee.zeus.domain.repository.UserRepository;
import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.exception.RoleNotFoundException;
import com.brihaspathee.zeus.exception.UserNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.UserMapper;
import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;
import com.brihaspathee.zeus.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, July 2022
 * Time: 12:28 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * The password encoder to encode the users password
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * The repository to perform CRUD operations
     */
    private final UserRepository userRepository;

    /**
     * The mapper to convert the entity to dto and vice versa
     */
    private final UserMapper userMapper;

    /**
     * Repository to check for roles
     */
    private final RoleRepository roleRepository;

    /**
     * Get all the users in the system
     * @return
     */
    @Override
    public UserList getAllUsers() {
        return UserList.builder()
                .userDtos(userMapper.usersToUserDtos(userRepository.findAll()))
                .build();
    }

    /**
     * Create a new user
     * @param userDto
     * @return
     */
    @Override
    public UserDto saveUser(UserDto userDto) {
        if(userDto.getUserId() != null){
            getUserById(userDto.getUserId());
        }
        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword("{bcrypt}"+encodedPassword);
        boolean rolesPresent = userDto.getRoleDtos().stream().allMatch(roleDto -> {
            if(roleDto.getRoleId() != null){
                return roleRepository.existsById(roleDto.getRoleId());
            }
            Optional<Role> role =  roleRepository.findRoleByRoleName(roleDto.getRoleName());

            if(role.isEmpty()){
                return false;
            }else{
                roleDto.setRoleId(role.get().getRoleId());
                return true;
            }
        });
        if(!rolesPresent){
            throw new RoleNotFoundException("One or more roles is not present");
        }
        User user = userMapper.userDtoToUser(userDto);
        log.info("User to be updated:{}", user);
        user = userRepository.save(user);
        user.setRoles(getAuthorities(user.getRoles()));
        log.info("Updated User:{}", user);

        return userMapper.userToUserDto(user);
    }

    /**
     * Get user by user id
     * @param userId
     * @return
     */
    @Override
    public UserList getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow( () -> {
            throw new UserNotFoundException("User with user id " + userId + " not found");
        });
        UserDto userDto = userMapper.userToUserDto(user);
        UserList userList = UserList.builder()
                .userDtos(Arrays.asList(userDto))
                .build();
        return userList;
    }

    /**
     * Get user by username
     * @param username
     * @return
     */
    @Override
    public UserList getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> {
            throw new UserNotFoundException("User with username " + username + " not found");
        });
        UserDto userDto = userMapper.userToUserDto(user);
        UserList userList = UserList.builder()
                .userDtos(Arrays.asList(userDto))
                .build();
        return userList;
    }

    /**
     * This method gets the authorities of each role and gives back the roles with
     * authorities
     * @param roles
     * @return
     */
    private Set<Role> getAuthorities(Set<Role> roles){
        Set<Role> rolesWithAuthorities = new HashSet<>();
        roles.stream().forEach(role -> {
            Role role1 = roleRepository.findById(role.getRoleId()).orElseThrow();
            rolesWithAuthorities.add(role1);
        });
        return rolesWithAuthorities;
    }
}
