package com.brihaspathee.zeus.web.model;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, January 2022
 * Time: 4:29 PM
 * Project: Zeus
 * Package Name: com.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalRefData {

    private String listCode;

    private String listTypeName;

    private String listCodeDesc;

    @Override
    public String toString() {
        return "InternalRefData{" +
                "listCode='" + listCode + '\'' +
                ", listTypeName='" + listTypeName + '\'' +
                ", listCodeDesc='" + listCodeDesc + '\'' +
                '}';
    }
}
