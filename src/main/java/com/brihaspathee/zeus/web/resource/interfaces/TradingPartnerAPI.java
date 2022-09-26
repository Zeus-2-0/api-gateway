package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.TradingPartnerCreatePermission;
import com.brihaspathee.zeus.permissions.TradingPartnerReadPermission;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
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
 * Date: 02, June 2022
 * Time: 11:41 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/tp")
//@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface TradingPartnerAPI {

    /**
     * Get the all trading partners in the system
     * @return Trading Partner List
     */
    @Operation(
            method = "GET",
            description = "Get all the trading partners",
            tags = {"trading-partner"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the trading partners",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TradingPartnerList.class))
                            }
                    )
            }
    )
    @GetMapping
    @TradingPartnerReadPermission
    ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllTradingPartners();

    /**
     * Get a trading partner details by trading partner id
     * @param tradingPartnerId
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get the trading partner by trading partner id",
            tags = {"trading-partner"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the trading partner",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TradingPartnerDto.class))
                            }
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = {
                                    @Content(mediaType = "application/json",schema = @Schema(implementation = TradingPartnerDto.class))
                            }),
            }
    )
    @TradingPartnerReadPermission
    @GetMapping("/{tradingPartnerId}")
    ResponseEntity<ZeusApiResponse<TradingPartnerDto>> getTradingPartnerById(@PathVariable("tradingPartnerId") String tradingPartnerId);

    /**
     * Get all the trading partners that match the search parameters
     * @param searchParams
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get all the trading partners",
            tags = {"trading-partner"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the trading partners",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TradingPartnerList.class))
                            }
                    )
            }
    )
    @TradingPartnerReadPermission
    @GetMapping("/search")
    ResponseEntity<ZeusApiResponse<TradingPartnerList>> getTradingPartnerByParameter(@RequestParam Map<String, String> searchParams);


    /**
     * Create a new Trading Partner
     * @param tradingPartnerDto
     * @return
     * @throws JsonProcessingException
     */
    @Operation(
            method = "POST",
            description = "Create a new trading partner",
            tags = {"trading-partner"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the trading partner",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TradingPartnerDto.class))
                            }
                    )
            }
    )
    @TradingPartnerCreatePermission
    @PostMapping
    ResponseEntity<ZeusApiResponse<TradingPartnerDto>> createTradingPartner(@RequestBody TradingPartnerDto tradingPartnerDto) throws JsonProcessingException;
}
