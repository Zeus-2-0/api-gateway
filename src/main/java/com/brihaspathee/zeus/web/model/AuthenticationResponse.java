package com.brihaspathee.zeus.web.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, May 2022
 * Time: 5:45 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class AuthenticationResponse {

    private final String jwtToken;

    private final UserDto userDto;

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                ", userDto=" + userDto +
                '}';
    }
}
