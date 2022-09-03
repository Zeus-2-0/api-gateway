package com.brihaspathee.zeus.web.request;

import com.brihaspathee.zeus.security.model.*;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, July 2022
 * Time: 4:47 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.request
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestAuthorityRequest {

    /**
     * Identifies if an exception is expected
     */
    private boolean exceptionExpected;

    /**
     * The exception code when an exception is expected
     */
    private String exceptionCode;

    /**
     * The exception message when an exception is expected
     */
    private String exceptionMessage;

    /**
     * The http status code expected
     */
    private String httpStatusCode;

    /**
     * Identifies if an auth exception is expected
     */
    private boolean authException;

    /**
     * The details of the logged in user
     */
    private UserDto loggedInUser;

    /**
     * the Authority that will be passed in the request
     */
    private AuthorityDto authorityDtoRequest;

    /**
     * The authorities that are expected in the output
     */
    private AuthorityList expectedAuthorityList;

    /**
     * The Authority that will be expected as an output
     */
    private AuthorityDto expectedAuthorityDto;

    /**
     * To string method
     * @return
     */
    @Override
    public String toString() {
        return "TestAuthorityRequest{" +
                "exceptionExpected=" + exceptionExpected +
                ", exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", authException=" + authException +
                ", loggedInUser=" + loggedInUser +
                ", authorityDtoRequest=" + authorityDtoRequest +
                ", expectedAuthorityList=" + expectedAuthorityList +
                ", expectedAuthorityDto=" + expectedAuthorityDto +
                '}';
    }
}
