package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.model.AccountList;
import com.brihaspathee.zeus.web.resource.interfaces.AccountAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, May 2022
 * Time: 5:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountAPIImpl implements AccountAPI {

    private final AccountService accountService;

    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAllAccounts() {
        AccountList accountList = AccountList.builder()
                .accountDtos(accountService.getAllAccounts())
                .build();
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountList)
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("Successful API Response")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAccountByParameters(Map<String, String> searchParams) {
        log.info("Query Parameters:{}", searchParams);
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountService.getAccountsByParams(searchParams))
                .message("API Success")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
