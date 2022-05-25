package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, May 2022
 * Time: 5:37 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private String roleName;

    private List<AuthorityDto> authorityDtos;

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleName='" + roleName + '\'' +
                ", authorityDtos=" + authorityDtos +
                '}';
    }
}
