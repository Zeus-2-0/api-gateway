package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, July 2022
 * Time: 12:25 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserService {

    /**
     * Get all the users
     * @return
     */
    UserList getAllUsers();

    /**
     * Create a new user
     * @param userDto
     * @return
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Get user by user id
     * @param userId
     * @return
     */
    UserList getUserById(UUID userId);

    /**
     * Get user by username
     * @param username
     * @return
     */
    UserList getUserByUsername(String username);
}
