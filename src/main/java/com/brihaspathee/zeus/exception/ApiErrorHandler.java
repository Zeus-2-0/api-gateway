package com.brihaspathee.zeus.exception;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<ZeusApiResponse<ApiExceptionList>> handleBadCredentialsException(BadCredentialsException exception) throws JsonProcessingException {
        log.info("Inside bad credentials exception handler");
        List<ApiException> errors = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .exceptionCode("100001")
                .exceptionMessage(exception.getMessage())
                .build();
        errors.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        ZeusApiResponse<ApiExceptionList> apiResponse = ZeusApiResponse.<ApiExceptionList>builder()
                //.timestamp(LocalDateTime.now())
                .response(exceptionList)
                .status(HttpStatus.UNAUTHORIZED)
                .statusCode(401)
                .reason(ApiResponseConstants.INVALID_PASSWORD)
                .message(ApiResponseConstants.FAIL)
                .developerMessage(ApiResponseConstants.FAILURE_REASON)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
    }

    /**
     * Method is used to handle the access denied exception
     * This is when the user is authenticated but is not authorized to perform the requested
     * operation
     * @param exception
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ZeusApiResponse<ApiExceptionList>> handleAccessDeniedException(AccessDeniedException exception) throws JsonProcessingException {
        log.info("Inside access denied exception handler");
        List<ApiException> errors = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .exceptionCode("100003")
                .exceptionMessage(exception.getMessage())
                .build();
        errors.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        ZeusApiResponse<ApiExceptionList> apiResponse = ZeusApiResponse.<ApiExceptionList>builder()
                //.timestamp(LocalDateTime.now())
                .response(exceptionList)
                .status(HttpStatus.FORBIDDEN)
                .statusCode(403)
                .reason(ApiResponseConstants.FORBIDDEN)
                .message(ApiResponseConstants.FAIL)
                .developerMessage(ApiResponseConstants.FAILURE_REASON)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Occurs when a user is not authenticated to access the API endpoint
     * @param exception
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ZeusApiResponse<ApiExceptionList>> handleClientErrorException(HttpClientErrorException exception) throws JsonProcessingException {
        log.info("Inside http client error exception: {}", exception.getRawStatusCode());
        if(exception.getRawStatusCode() == 401){
            ZeusApiResponse apiResponse = ZeusApiResponse.builder()
                    //.timestamp(LocalDateTime.now())
                    .message("UNAUTHORIZED")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(401)
                    .response(AuthenticationResponse.builder()
                            .authMessage("Authentication Failed")
                            .isAuthenticated(false)
                            .build())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        }else {
            ZeusApiResponse apiResponse = ZeusApiResponse.builder()
                    //.timestamp(LocalDateTime.now())
                    .message("Unknown exception occured")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .statusCode(500)
                    .response(AuthenticationResponse.builder()
                            .authMessage("Authentication Failed")
                            .isAuthenticated(false)
                            .build())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
