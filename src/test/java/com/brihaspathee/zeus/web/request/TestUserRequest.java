package com.brihaspathee.zeus.web.request;

import com.brihaspathee.zeus.web.model.UserDto;
import com.brihaspathee.zeus.web.model.UserList;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 14, July 2022
 * Time: 2:16 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.request
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestUserRequest {

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
     * The user information passed in as the request
     */
    private UserDto userDtoRequest;

    /**
     * The user list that is expected as a result
     */
    private UserList expectedUserList;

    /**
     * The user dto that is expected
     */
    private UserDto expectedUserDto;
}
