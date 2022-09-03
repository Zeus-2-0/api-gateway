package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.security.model.AuthorityDto;
import com.brihaspathee.zeus.security.model.AuthorityList;
import com.brihaspathee.zeus.service.interfaces.AuthorityService;
import com.brihaspathee.zeus.web.resource.interfaces.AuthorityAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, July 2022
 * Time: 4:28 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorityAPIImpl implements AuthorityAPI {

    /**
     * The authority service that is used to get the details
     */
    private final AuthorityService authorityService;

    /**
     * Get all the permissions that are present in the system
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityList>> getAllAuthorities() {
        AuthorityList authorityList = authorityService.getAllAuthorities();
        ZeusApiResponse<AuthorityList> apiResponse = ZeusApiResponse.<AuthorityList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(authorityList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get the permission by authority id
     * @param authorityId
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorityById(UUID authorityId) {
        AuthorityList authorityList = authorityService.getAuthorityById(authorityId);
        ZeusApiResponse<AuthorityList> apiResponse = ZeusApiResponse.<AuthorityList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(authorityList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get the permission by authority name
     * @param permission
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorityByName(String permission) {
        AuthorityList authorityList = authorityService.getAuthorityByName(permission);
        ZeusApiResponse<AuthorityList> apiResponse = ZeusApiResponse.<AuthorityList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(authorityList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Create a new authority
     * @param authorityDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> createAuthority(AuthorityDto authorityDto) {
        AuthorityDto savedAuthority = authorityService.saveAuthority(authorityDto);
        ZeusApiResponse<AuthorityDto> apiResponse = ZeusApiResponse.<AuthorityDto>builder()
                .response(savedAuthority)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<ZeusApiResponse<AuthorityDto>>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Update an existing authority
     * @param authorityId
     * @param authorityDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> updateAuthority(UUID authorityId, AuthorityDto authorityDto) {
        authorityDto.setAuthorityId(authorityId);
        AuthorityDto savedAuthority = authorityService.saveAuthority(authorityDto);
        ZeusApiResponse<AuthorityDto> apiResponse = ZeusApiResponse.<AuthorityDto>builder()
                .response(savedAuthority)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .statusCode(204)
                .status(HttpStatus.NO_CONTENT)
                .build();
        return new ResponseEntity<ZeusApiResponse<AuthorityDto>>(apiResponse, HttpStatus.NO_CONTENT);
    }
}
