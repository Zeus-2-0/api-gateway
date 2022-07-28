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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
