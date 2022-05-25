package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.web.model.AuthenticationRequest;
import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import com.brihaspathee.zeus.web.model.UserDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, May 2022
 * Time: 5:36 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface APIGatewayUserService {

    AuthenticationResponse getAuthenticationResponse(String username);
    AuthenticationResponse getAuthenticationResponse(User user);
}
