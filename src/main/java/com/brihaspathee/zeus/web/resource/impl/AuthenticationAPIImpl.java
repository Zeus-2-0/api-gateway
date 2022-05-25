package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.auth.ZeusUserDetailsService;
import com.brihaspathee.zeus.auth.util.ZeusJwtUtil;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.service.interfaces.APIGatewayUserService;
import com.brihaspathee.zeus.web.model.AuthenticationDto;
import com.brihaspathee.zeus.web.model.AuthenticationRequest;
import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import com.brihaspathee.zeus.web.resource.interfaces.AuthenticationAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 4:42 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationAPIImpl implements AuthenticationAPI {

    private final AuthenticationManager authenticationManager;

    private final APIGatewayUserService apiGatewayUserService;


    @Override
    public AuthenticationResponse jwtAuthentication(AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }

//        final User userDetails = zeusUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = zeusJwtUtil.generateToken(userDetails);
//        return AuthenticationResponse.builder().jwtToken(jwt).build();
        return apiGatewayUserService.getAuthenticationResponse(authenticationRequest.getUsername());
    }

    @Override
    public AuthenticationResponse basicAuthentication(User user) throws Exception {
//        final String jwt = zeusJwtUtil.generateToken(user);
//        return AuthenticationResponse.builder().jwtToken(jwt).build();
        return apiGatewayUserService.getAuthenticationResponse(user);
    }
}
