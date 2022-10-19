package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.resource.interfaces.AccountAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    /**
     * The account service method to get the account related details
     */
    private final AccountService accountService;

    /**
     * Get all the accounts
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAllAccounts() {
        AccountList accountList = accountService.getAllAccounts();
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountList)
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("Successful API Response")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get the account by search parameters
     * @param searchParams
     * @return
     */
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

    /**
     * Get account by account number
     * @param accountNumber
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<AccountList>> getAccountByAccountNumber(String accountNumber) {
        ZeusApiResponse<AccountList> apiResponse = ZeusApiResponse.<AccountList>builder()
                .response(accountService.getAccountByAccountNumber(accountNumber))
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<MemberDto>> getMemberByMemberCode(String memberCode) {
        log.info("The member code:{}", memberCode);
        ZeusApiResponse<MemberDto> apiResponse = ZeusApiResponse.<MemberDto>builder()
                .response(accountService.getMemberByMemberCode(memberCode))
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(200)
                .message(ApiResponseConstants.SUCCESS)
                .developerMessage(ApiResponseConstants.SUCCESS_REASON)
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
