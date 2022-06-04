package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.web.model.WelcomeDto;
import com.brihaspathee.zeus.web.resource.interfaces.WelcomeAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, May 2022
 * Time: 12:11 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class WelcomeAPIImpl implements WelcomeAPI {


    @Override
    public WelcomeDto welcomeUser(String username) {
        return WelcomeDto.builder().username(username).build();
    }

    @Override
    public ResponseEntity<ZeusApiResponse<WelcomeDto>> welcome() {
        WelcomeDto welcomeDto =  WelcomeDto.builder().username("Hello to the World!!!").build();
        ZeusApiResponse<WelcomeDto> apiResponse = ZeusApiResponse.<WelcomeDto>builder()
                .response(welcomeDto)
                .message("API Call Successful")
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
