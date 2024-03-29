package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.permissions.RoleCreatePermission;
import com.brihaspathee.zeus.permissions.RoleReadPermission;
import com.brihaspathee.zeus.permissions.RoleUpdatePermission;
import com.brihaspathee.zeus.security.model.RoleDto;
import com.brihaspathee.zeus.security.model.RoleList;
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
 * Time: 4:03 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/security/role")
@Validated
public interface RoleAPI {

    /**
     * Get all the roles that are present in the system
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get all the roles in the system",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the roles",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @RoleReadPermission
    ResponseEntity<ZeusApiResponse<RoleList>> getAllRoles();


    /**
     * Get Role by role id
     * @param roleId
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get the role by role id",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the respective role",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/role-id/{roleId}")
    @RoleReadPermission
    ResponseEntity<ZeusApiResponse<RoleList>> getRoleById(@PathVariable UUID roleId);

    /**
     * Get Role by role name
     * @param roleName
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get the role by role name",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the respective role",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/role-name/{roleName}")
    @RoleReadPermission
    ResponseEntity<ZeusApiResponse<RoleList>> getRoleByRoleName(@PathVariable String roleName);

    /**
     * Create a new Role
     * @return
     */
    @Operation(
            method = "POST",
            description = "Create a new role",
            tags = {"security"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the role",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = RoleDto.class))
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
    @RoleCreatePermission
    ResponseEntity<ZeusApiResponse<RoleDto>> createRole(@RequestBody RoleDto roleDto);

    /**
     * Update an existing Role
     * @return
     */
    @Operation(
            method = "PUT",
            description = "Update an existing role",
            tags = {"security"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Successfully update the role"),
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
    @PutMapping("/{roleId}")
    @RoleUpdatePermission
    ResponseEntity<ZeusApiResponse<RoleDto>> updateRole(@PathVariable UUID roleId, @RequestBody RoleDto roleDto);
}
