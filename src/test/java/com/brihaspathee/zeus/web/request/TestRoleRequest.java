package com.brihaspathee.zeus.web.request;

import com.brihaspathee.zeus.security.model.RoleDto;
import com.brihaspathee.zeus.security.model.RoleList;
import com.brihaspathee.zeus.security.model.UserDto;
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
public class TestRoleRequest {

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
     * the role that will be passed in the request
     */
    private RoleDto roleDtoRequest;

    /**
     * The roles that are expected in the output
     */
    private RoleList expectedRoleList;

    /**
     * The role that will be expected as an output
     */
    private RoleDto expectedRoleDto;

    /**
     * To string method
     * @return
     */
    @Override
    public String toString() {
        return "TestRoleRequest{" +
                "exceptionExpected=" + exceptionExpected +
                ", exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", authException=" + authException +
                ", loggedInUser=" + loggedInUser +
                ", roleDtoRequest=" + roleDtoRequest +
                ", expectedRoleList=" + expectedRoleList +
                ", expectedRoleDto=" + expectedRoleDto +
                '}';
    }
}
