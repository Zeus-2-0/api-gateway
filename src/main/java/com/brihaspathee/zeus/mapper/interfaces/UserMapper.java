package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.security.model.UserDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 2:01 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserMapper {

    /**
     * Converts user entity to User dto
     * @param user
     * @return
     */
    UserDto userToUserDto(User user);

    /**
     * Converts the user dto to user entity
     * @param userDto
     * @return
     */
    User userDtoToUser(UserDto userDto);

    /**
     * Converts the list of user dto to user entities
     * @param userDtos
     * @return
     */
    List<User> userDtosToUsers(List<UserDto> userDtos);

    /**
     * Converts the list of user entities to user dtos
     * @param users
     * @return
     */
    List<UserDto> usersToUserDtos(List<User> users);
}
