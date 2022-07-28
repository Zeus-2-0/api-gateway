package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.mapper.interfaces.RoleMapper;
import com.brihaspathee.zeus.mapper.interfaces.UserMapper;
import com.brihaspathee.zeus.security.model.UserDto;
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
 * Time: 2:04 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    /**
     * Role mapper to map all the roles that are associated with the user
     */
    private final RoleMapper roleMapper;

    /**
     * Converts user entity to User dto
     * @param user
     * @return
     */
    @Override
    public UserDto userToUserDto(User user) {
        if (user == null){
            return null;
        }
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        if (user.getRoles() != null && !user.getRoles().isEmpty()){
            userDto.setRoleDtos(
                    roleMapper.rolesToRoleDtos(
                            user.getRoles().stream().collect(Collectors.toList())));
        }
        return userDto;
    }

    /**
     * Converts the user dto to user entity
     * @param userDto
     * @return
     */
    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null){
            return null;
        }
        User user = User.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .createdDate(LocalDateTime.now())
                .build();
        if (userDto.getRoleDtos() != null && !userDto.getRoleDtos().isEmpty()){
            user.setRoles(
                    roleMapper.roleDtosToRoles(
                            userDto.getRoleDtos()).stream().collect(Collectors.toSet()));
        }
        return user;
    }

    /**
     * Converts the list of user dto to user entities
     * @param userDtos
     * @return
     */
    @Override
    public List<User> userDtosToUsers(List<UserDto> userDtos) {

        return userDtos.stream().map(this::userDtoToUser).collect(Collectors.toList());
    }

    /**
     * Converts the list of user entities to user dtos
     * @param users
     * @return
     */
    @Override
    public List<UserDto> usersToUserDtos(List<User> users) {

        return users.stream().map(this::userToUserDto).collect(Collectors.toList());
    }
}
