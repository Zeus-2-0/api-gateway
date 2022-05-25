package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.web.model.WelcomeDto;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2022
 * Time: 12:06 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus")
@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface WelcomeAPI {

    /**
     * Get the account related to the account id that is passed in as input
     * @param username
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get a Welcome Message for the user",
            tags = {"welcome"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved ta welcome message for the member",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WelcomeDto.class))
                            }
                    )
            }
    )
    @GetMapping("/{username}")
    WelcomeDto welcomeUser(@PathVariable String username);

    /**
     * Get the account related to the account id that is passed in as input
     * @return static welcome message
     */
    @Operation(
            method = "GET",
            description = "Get a generic Welcome",
            tags = {"welcome"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved a generic welcome message",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WelcomeDto.class))
                            }
                    )
            }
    )
    @GetMapping("/welcome")
    WelcomeDto welcome();
}
