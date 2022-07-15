package com.brihaspathee.zeus.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, July 2022
 * Time: 10:13 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalListTypeDto {

    /**
     * The key of the internal list type
     */
    @JsonProperty(required = false)
    @Schema(description = "The key of the internal list type", example = "1001")
    private Long internalListTypeSK;

    /**
     * The name of the internal list type
     */
    @JsonProperty(required = true)
    @Schema(description = "Internal list type name", example = "Identifier")
    private String internalListTypeName;

    /**
     * The description of the internal list type
     */
    @JsonProperty(required = false)
    @Schema(description = "Internal list type description", example = "Contains the list of all the internal identifiers")
    private String internalListTypeDesc;

    /**
     * The list of codes that are associated with the internal list
     */
    @JsonProperty(required = false)
    @Schema(description = "The list of the internal ref-data codes that are in the internal list")
    private List<InternalRefData> internalRefDataList;
}
