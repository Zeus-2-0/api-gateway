package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, July 2022
 * Time: 12:21 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleList {

    /**
     * The list roles
     */
    private List<RoleDto> roleDtos;

    @Override
    public String toString() {
        return "RoleList{" +
                "roleDtos=" + roleDtos +
                '}';
    }
}
