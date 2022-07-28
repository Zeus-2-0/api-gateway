package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.ReferenceDataReadPermission;
import com.brihaspathee.zeus.reference.data.model.InternalListTypeDto;
import com.brihaspathee.zeus.reference.data.model.InternalListTypesDto;
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

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 2:05 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/ref-data/internal/list-types")
//@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface ReferenceDataAPI {

    /**
     * Get all the internal codes of a list type
     * @param listTypeName
     * @return Internal Reference Data List
     */
    @Operation(
            operationId = "Get Internal Codes for List Type",
            method = "GET",
            description = "Get all the codes of a reference data list type",
            tags = {"reference-data"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the codes of an internal list",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternalListTypeDto.class))
                            }
                    )
            }
    )
    @GetMapping("/{listTypeName}")
    @ReferenceDataReadPermission
    ResponseEntity<ZeusApiResponse<InternalListTypeDto>> getInternalRefDataList(@PathVariable("listTypeName") String listTypeName);

    /**
     * Get all the internal lists that are present in the system
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get all the internal list types that are present in the system",
            tags = {"reference-data"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all Internal List types",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternalListTypesDto.class))
                            }
                    )
            }
    )
    @GetMapping
    ResponseEntity<ZeusApiResponse<InternalListTypesDto>> getAllInternalListTypes();

    /**
     * Get all the internal codes of a list type
     * @param internalListTypesDto
     * @return Internal Reference Data List
     */
    @Operation(
            operationId = "Get Internal Codes for List Types",
            method = "GET",
            description = "Get all the codes of a reference data list types",
            tags = {"reference-data"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the codes of an internal lists",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternalListTypesDto.class))
                            }
                    )
            }
    )
    @PostMapping
    @ReferenceDataReadPermission
    ResponseEntity<ZeusApiResponse<InternalListTypesDto>> getInternalCodesForListTypes(@RequestBody InternalListTypesDto internalListTypesDto);

}
