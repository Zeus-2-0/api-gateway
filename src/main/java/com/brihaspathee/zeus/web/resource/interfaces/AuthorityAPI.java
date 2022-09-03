package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.exception.ApiExceptionList;
import com.brihaspathee.zeus.permissions.AuthorityCreatePermission;
import com.brihaspathee.zeus.permissions.AuthorityReadPermission;
import com.brihaspathee.zeus.security.model.AuthorityDto;
import com.brihaspathee.zeus.security.model.AuthorityList;
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
@RequestMapping("/api/v1/zeus/security/authority")
@Validated
public interface AuthorityAPI {

    /**
     * Get all the permissions that are present in the system
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get all the permissions in the system",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the Permissions",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @AuthorityReadPermission
    ResponseEntity<ZeusApiResponse<AuthorityList>> getAllAuthorities();

    /**
     * Get permission for a specific authority by id
     * @param authorityId
     * @return
     */
    @Operation(
            operationId = "Get Authority by authority id",
            method = "GET",
            description = "Get permission for a specific authority by id",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the permission",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/authority-id/{authorityId}")
    @AuthorityReadPermission
    ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorityById(@PathVariable UUID authorityId);

    /**
     * Get permission for a specific authority by permission name
     * @param permission
     * @return
     */
    @Operation(
            operationId = "Get Authority by permission name",
            method = "GET",
            description = "Get permission for a specific authority by permission name",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the permission",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/authority-name/{permission}")
    @AuthorityReadPermission
    ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorityByName(@PathVariable String permission);

    /**
     * Create a new Role
     * @return
     */
    @Operation(
            method = "POST",
            description = "Create a new authority in the system",
            tags = {"security"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the authority",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AuthorityDto.class))
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
    @AuthorityCreatePermission
    ResponseEntity<ZeusApiResponse<AuthorityDto>> createAuthority(@RequestBody AuthorityDto authorityDto);

    /**
     * Update an existing Role
     * @return
     */
    @Operation(
            operationId = "Update an existing authority id",
            method = "PUT",
            description = "Update an existing authority in the system",
            tags = {"security"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Successfully update the authority"),
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
    @PutMapping("/{authorityId}")
    @AuthorityCreatePermission
    ResponseEntity<ZeusApiResponse<AuthorityDto>> updateAuthority(@PathVariable UUID authorityId,
                                                                  @RequestBody AuthorityDto authorityDto);
}
