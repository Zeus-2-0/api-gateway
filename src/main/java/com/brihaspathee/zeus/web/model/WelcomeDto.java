package com.brihaspathee.zeus.web.model;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2022
 * Time: 12:06 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WelcomeDto {

    private String username;

    @Override
    public String toString() {
        return "WelcomeDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
