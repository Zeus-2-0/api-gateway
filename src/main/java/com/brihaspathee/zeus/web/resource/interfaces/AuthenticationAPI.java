package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.permissions.TradingPartnerCreatePermission;
import com.brihaspathee.zeus.web.model.AuthenticationDto;
import com.brihaspathee.zeus.web.model.AuthenticationRequest;
import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 4:41 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/zeus")
// @CrossOrigin(origins = "http://zeus-ui:80")
@Validated
public interface AuthenticationAPI {

    /**
     *
     * @param authenticationRequest
     * @return
     */
    @Operation(
            method = "POST",
            description = "Create a new trading partner",
            tags = {"trading-partner"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the trading partner",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AuthenticationResponse.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AuthenticationResponse.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = AuthenticationResponse.class))
                    })
    })
    @PostMapping(path = "/jwt/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZeusApiResponse<AuthenticationResponse>> jwtAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception;

    @GetMapping("/basic/authenticate")
    ResponseEntity<ZeusApiResponse<AuthenticationResponse>> basicAuthentication(@AuthenticationPrincipal User user) throws Exception;
}
