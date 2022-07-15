package com.brihaspathee.zeus.exception;

import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 14, July 2022
 * Time: 8:16 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@ControllerAdvice
public class ApiErrorHandler {

    /**
     * Handles the bad credentials exception.
     * This exception is generated when an invalid password is provided by the user
     * @param exception
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ZeusApiResponse<AuthenticationResponse>> handleBadCredentialsException(BadCredentialsException exception) throws JsonProcessingException {
        log.info("Inside bad credentials exception handler");
        return invalidCredentials("Authentication Failed. Invalid Password");
    }


    /**
     * Handles user not found exception
     * This exception is generated when the username is not found
     * @param exception
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ZeusApiResponse<AuthenticationResponse>> handleBadCredentialsException(UserNotFoundException exception) throws JsonProcessingException {
        log.info("Inside User not found exception");
        return invalidCredentials("Authentication Failed. User not found");
    }

    /**
     * Method that is used commonly for both invalid username and invalid password
     * @param message
     * @return
     */
    private ResponseEntity<ZeusApiResponse<AuthenticationResponse>> invalidCredentials(String message) {
        ZeusApiResponse apiResponse = ZeusApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .status(HttpStatus.UNAUTHORIZED)
                .statusCode(401)
                .response(AuthenticationResponse.builder()
                        .authMessage(message)
                        .isAuthenticated(false)
                        .build())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}
