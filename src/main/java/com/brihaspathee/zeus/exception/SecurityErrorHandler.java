package com.brihaspathee.zeus.exception;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 10, August 2022
 * Time: 2:25 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@ControllerAdvice
public class SecurityErrorHandler {

    /**
     * Handles user not found exception
     * This exception is generated when the username is not found
     * @param exception
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ZeusApiResponse<ApiExceptionList>> handleUserNotFoundException(UserNotFoundException exception) throws JsonProcessingException {
        log.info("Inside User not found exception");
        List<ApiException> errors = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .exceptionCode("100002")
                .exceptionMessage(exception.getMessage())
                .build();
        errors.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        ZeusApiResponse<ApiExceptionList> apiResponse = ZeusApiResponse.<ApiExceptionList>builder()
                //.timestamp(LocalDateTime.now())
                .response(exceptionList)
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(400)
                .reason(ApiResponseConstants.FAIL)
                .message(ApiResponseConstants.FAIL)
                .developerMessage(ApiResponseConstants.FAILURE_REASON)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles role not found exception
     * This exception is generated when the role that the user is searching is not found
     * @param exception
     * @return
     */
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ZeusApiResponse<ApiExceptionList>> handleRoleNotFoundException(RoleNotFoundException exception) {
        log.info("Inside role not found exception");
        List<ApiException> errors = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .exceptionCode("100005")
                .exceptionMessage(exception.getMessage())
                .build();
        errors.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        ZeusApiResponse<ApiExceptionList> apiResponse = ZeusApiResponse.<ApiExceptionList>builder()
                //.timestamp(LocalDateTime.now())
                .response(exceptionList)
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(400)
                .reason(ApiResponseConstants.FAIL)
                .message(ApiResponseConstants.FAIL)
                .developerMessage(ApiResponseConstants.FAILURE_REASON)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles authority not found exception
     * This exception is generated when the authority that the user is searching is not found
     * @param exception
     * @return
     */
    @ExceptionHandler(AuthorityNotFoundException.class)
    public ResponseEntity<ZeusApiResponse<ApiExceptionList>> handleAuthorityNotFoundException(AuthorityNotFoundException exception) {
        log.info("Inside authority not found exception");
        List<ApiException> errors = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .exceptionCode("100004")
                .exceptionMessage(exception.getMessage())
                .build();
        errors.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().exceptions(errors).build();
        ZeusApiResponse<ApiExceptionList> apiResponse = ZeusApiResponse.<ApiExceptionList>builder()
                //.timestamp(LocalDateTime.now())
                .response(exceptionList)
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(400)
                .reason(ApiResponseConstants.FAIL)
                .message(ApiResponseConstants.FAIL)
                .developerMessage(ApiResponseConstants.FAILURE_REASON)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
