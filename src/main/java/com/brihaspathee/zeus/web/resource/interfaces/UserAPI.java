package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.permissions.UserCreatePermission;
import com.brihaspathee.zeus.permissions.UserReadPermission;
import com.brihaspathee.zeus.permissions.UserUpdatePermission;
import com.brihaspathee.zeus.security.model.UserDto;
import com.brihaspathee.zeus.security.model.UserList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, July 2022
 * Time: 4:02 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/security/user")
@Validated
public interface UserAPI {

    /**
     * Get all the users that are present in the system
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get all the users in the system",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the users",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @UserReadPermission
    ResponseEntity<ZeusApiResponse<UserList>> getAllUsers();

    /**
     * Get the user by user id
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get user by user id",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the user",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/user-id/{userId}")
    @UserReadPermission
    ResponseEntity<ZeusApiResponse<UserList>> getUserById(@PathVariable UUID userId);

    /**
     * Get the user by username
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get user by username",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the user",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/username/{username}")
    @UserReadPermission
    ResponseEntity<ZeusApiResponse<UserList>> getUserByUsername(@PathVariable String username);

    /**
     * Create a new User
     * @return
     */
    @Operation(
            method = "POST",
            description = "Create a new user",
            tags = {"security"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the user",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @PostMapping
    @UserCreatePermission
    ResponseEntity<ZeusApiResponse<UserDto>> createUser(@RequestBody UserDto userDto);

    /**
     * Update an existing user
     * @return
     */
    @Operation(
            method = "PUT",
            description = "Update an existing user",
            tags = {"security"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully updated the user",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @PutMapping("/{userId}")
    @UserUpdatePermission
    ResponseEntity<ZeusApiResponse<UserDto>> updateUser(@PathVariable UUID userId, @RequestBody UserDto userDto);
}
