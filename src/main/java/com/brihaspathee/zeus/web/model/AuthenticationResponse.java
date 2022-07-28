package com.brihaspathee.zeus.web.model;

import com.brihaspathee.zeus.security.model.UserDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private boolean isAuthenticated;

    private String authMessage;

    private String authToken;

    private LocalDateTime authExpiration;

    private UserDto userDto;

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "isAuthenticated=" + isAuthenticated +
                ", authMessage='" + authMessage + '\'' +
                ", authToken='" + authToken + '\'' +
                ", authExpiration=" + authExpiration +
                ", userDto=" + userDto +
                '}';
    }
}
