package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.auth.ZeusUserDetailsService;
import com.brihaspathee.zeus.auth.util.ZeusJwtUtil;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.service.interfaces.APIGatewayUserService;
import com.brihaspathee.zeus.web.model.AuthenticationDto;
import com.brihaspathee.zeus.web.model.AuthenticationRequest;
import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import com.brihaspathee.zeus.web.resource.interfaces.AuthenticationAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 4:42 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationAPIImpl implements AuthenticationAPI {

    private final AuthenticationManager authenticationManager;

    private final APIGatewayUserService apiGatewayUserService;


    /**
     * Authenticates the username and password that is passed in the authentication request
     * The authentication request is passed in the body of the request
     * @param authenticationRequest
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthenticationResponse>> jwtAuthentication(AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
//            throw new Exception("Incorrect username or password");
            throw new BadCredentialsException("Incorrect password provided for the user: " + authenticationRequest.getUsername());
        }

//        final User userDetails = zeusUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = zeusJwtUtil.generateToken(userDetails);
//        return AuthenticationResponse.builder().jwtToken(jwt).build();
        AuthenticationResponse authenticationResponse = apiGatewayUserService.getAuthenticationResponse(authenticationRequest.getUsername());
        log.info("Authentication Response: {}", authenticationResponse);
        return getAuthenticatedResponse(authenticationResponse);
    }

    /**
     * Authenticates the username and password
     * @param user
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthenticationResponse>> basicAuthentication(User user) throws Exception {
//        final String jwt = zeusJwtUtil.generateToken(user);
//        return AuthenticationResponse.builder().jwtToken(jwt).build();
        AuthenticationResponse authenticationResponse = apiGatewayUserService.getAuthenticationResponse(user);
        return getAuthenticatedResponse(authenticationResponse);
    }

    /**
     * Creates the authentication response after successful authentication of the user
     * @param authenticationResponse
     * @return
     */
    private ResponseEntity<ZeusApiResponse<AuthenticationResponse>> getAuthenticatedResponse(AuthenticationResponse authenticationResponse){
        ZeusApiResponse<AuthenticationResponse> apiResponse = ZeusApiResponse.<AuthenticationResponse>builder()
                //.timestamp(LocalDateTime.now())
                .response(authenticationResponse)
                .status(HttpStatus.OK)
                .statusCode(200)
                .message("Authenticated Successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
