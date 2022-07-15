package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, May 2022
 * Time: 5:38 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /**
     * The unique user id that is associated with the user
     */
    private UUID userId;

    /**
     * The user name of the user
     */
    private String username;

    /**
     * The password of the user
     */
    private String password;

    /**
     * The roles that are associated with the user
     */
    private List<RoleDto> roleDtos;

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleDtos=" + roleDtos +
                '}';
    }
}
