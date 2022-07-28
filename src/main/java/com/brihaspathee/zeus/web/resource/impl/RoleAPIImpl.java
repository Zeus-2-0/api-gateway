package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.security.model.RoleDto;
import com.brihaspathee.zeus.security.model.RoleList;
import com.brihaspathee.zeus.service.interfaces.RoleService;
import com.brihaspathee.zeus.web.resource.interfaces.RoleAPI;
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
 * Date: 07, July 2022
 * Time: 12:37 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleAPIImpl implements RoleAPI {

    /**
     * The role service that is used to get the details
     */
    private final RoleService roleService;


    /**
     * Get all the roles in the system
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<RoleList>> getAllRoles() {
        RoleList roleList = roleService.getAllRoles();
        ZeusApiResponse<RoleList> apiResponse = ZeusApiResponse.<RoleList>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .response(roleList)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Create a new role
     * @param roleDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<RoleDto>> createRole(RoleDto roleDto) {
        log.info("Role Dto:{}", roleDto);
        RoleDto savedRole = roleService.saveRole(roleDto);
        ZeusApiResponse<RoleDto> apiResponse = ZeusApiResponse.<RoleDto>builder()
                .response(savedRole)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<ZeusApiResponse<RoleDto>>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Update an existing role
     * @param roleId
     * @param roleDto
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<RoleDto>> updateRole(UUID roleId, RoleDto roleDto) {
        log.info("Role Dto:{}", roleDto);
        //roleDto.setRoleId(roleId);
        RoleDto savedRole = roleService.saveRole(roleDto);
        ZeusApiResponse<RoleDto> apiResponse = ZeusApiResponse.<RoleDto>builder()
                .response(savedRole)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<ZeusApiResponse<RoleDto>>(apiResponse, HttpStatus.CREATED);
    }
}
