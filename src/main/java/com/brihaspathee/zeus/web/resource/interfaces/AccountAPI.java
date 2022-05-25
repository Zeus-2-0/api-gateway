package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.AccountReadPermission;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.WelcomeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, May 2022
 * Time: 5:50 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/account")
@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface AccountAPI {

    /**
     * Get the account related to the account id that is passed in as input
     * @return List AccountDto
     */
    @Operation(
            method = "GET",
            description = "Get all the accounts",
            tags = {"accounts"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the accounts",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AccountDto.class))
                            }
                    )
            }
    )
    @GetMapping
    @AccountReadPermission
    List<AccountDto> getAllAccounts();
}
