package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.ReferenceDataReadPermission;
import com.brihaspathee.zeus.permissions.TradingPartnerReadPermission;
import com.brihaspathee.zeus.web.model.InternalRefDataList;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
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
 * Date: 02, June 2022
 * Time: 2:05 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/zeus/internal/refdata")
@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface ReferenceDataAPI {

    /**
     * Get all the internal codes of a list type
     * @return Internal Reference Data List
     */
    @Operation(
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
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InternalRefDataList.class))
                            }
                    )
            }
    )
    @GetMapping("/{listTypeName}")
    @ReferenceDataReadPermission
    InternalRefDataList getInternalRefDataList(@PathVariable("listTypeName") String listTypeName);
}
