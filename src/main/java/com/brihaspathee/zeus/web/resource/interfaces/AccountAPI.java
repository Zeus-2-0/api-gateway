package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.permissions.AccountReadPermission;
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

import java.util.Map;

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
//@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface AccountAPI {

    /**
     * Get the account that is related to the account id that is passed in as input
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
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @AccountReadPermission
    ResponseEntity<ZeusApiResponse<AccountList>> getAllAccounts();

    /**
     * Get accounts based on the search parameters passed
     * @param searchParams
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get accounts based on the search parameters",
            tags = {"accounts"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the accounts that matched the search params",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/search")
    @AccountReadPermission
    ResponseEntity<ZeusApiResponse<AccountList>> getAccountByParameters(@RequestParam Map<String, String> searchParams);

    /**
     * Get account information by account number
     * @param accountNumber
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get account by account number",
            tags = {"accounts"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the account by account number",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/{accountNumber}")
    @AccountReadPermission
    ResponseEntity<ZeusApiResponse<AccountList>> getAccountByAccountNumber(@PathVariable String accountNumber);

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get member by member code",
            tags = {"accounts"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the member by member code",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping("/member/{memberCode}")
    @AccountReadPermission
    ResponseEntity<ZeusApiResponse<MemberDto>> getMemberByMemberCode(@PathVariable String memberCode);
}
