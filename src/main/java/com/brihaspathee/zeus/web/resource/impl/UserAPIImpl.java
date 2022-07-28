package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;
import com.brihaspathee.zeus.service.interfaces.UserService;
import com.brihaspathee.zeus.web.resource.interfaces.UserAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, July 2022
 * Time: 12:35 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAPIImpl implements UserAPI {

    /**
     * The user service that is used to get the details
     */
    private final UserService userService;

    /**
     * Get all the users in the system
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<UserList>> getAllUsers() {
        UserList userList = userService.getAllUsers();
        ZeusApiResponse<UserList> apiResponse = ZeusApiResponse.<UserList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(userList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get user by user id
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<UserList>> getUserById(UUID userId) {
        UserList userList = userService.getUserById(userId);
        ZeusApiResponse<UserList> apiResponse = ZeusApiResponse.<UserList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(userList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get user by username
     * @param username
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<UserList>> getUserByUsername(String username) {
        UserList userList = userService.getUserByUsername(username);
        ZeusApiResponse<UserList> apiResponse = ZeusApiResponse.<UserList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(userList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Create a new user
     * @param userDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<UserDto>> createUser(UserDto userDto) {
        UserDto savedUser = userService.saveUser(userDto);
        ZeusApiResponse<UserDto> apiResponse = ZeusApiResponse.<UserDto>builder()
                .response(savedUser)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<ZeusApiResponse<UserDto>>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Update an existing user
     * @param userId
     * @param userDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<UserDto>> updateUser(UUID userId, UserDto userDto) {
        //userDto.setUserId(userId);
        UserDto savedUser = userService.saveUser(userDto);
        ZeusApiResponse<UserDto> apiResponse = ZeusApiResponse.<UserDto>builder()
                .response(savedUser)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<ZeusApiResponse<UserDto>>(apiResponse, HttpStatus.CREATED);
    }
}
