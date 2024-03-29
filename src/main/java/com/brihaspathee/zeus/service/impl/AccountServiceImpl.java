package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.dto.account.AccountDto;
import com.brihaspathee.zeus.dto.account.AccountList;
import com.brihaspathee.zeus.dto.account.MemberDto;
import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, May 2022
 * Time: 5:44 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    /**
     * The reference data host
     */
    @Value("${url.host.member-mgmt}")
    private String memberMgmtHost;

    /**
     * Rest-template to connect with other rest APIs
     */
    private final RestTemplate restTemplate;

    /**
     * Webclient to connect with other rest APIs
     */
    private final WebClient webClient;

    /**
     * Get all the accounts in the system
     * @return
     */
    @Override
    public AccountList getAllAccounts() {
        ZeusApiResponse<AccountList> apiResponse = webClient.get()
                .uri(memberMgmtHost+"zeus/account")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ZeusApiResponse<AccountList>>() {})
                .block();
        return apiResponse.getResponse();
    }

    /**
     * Get all the accounts by using the search parameters
     * @param searchParams
     * @return
     */
    @Override
    public AccountList getAccountsByParams(Map<String, String> searchParams) {
        String accountNumber = searchParams.get("account-number");
        return getAccountByAccountNumber(accountNumber);
    }

    /**
     * Get account by account number
     * @param accountNumber
     * @return
     */
    @Override
    public AccountList getAccountByAccountNumber(String accountNumber) {
        log.info("Account Number of the account:{}",accountNumber);
        ZeusApiResponse<AccountList> apiResponse = webClient.get()
                .uri(memberMgmtHost+"zeus/account/"+accountNumber)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ZeusApiResponse<AccountList>>() {})
                .block();
        log.info("Account returned:{}", apiResponse.getResponse());
        return apiResponse.getResponse();
    }

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    @Override
    public MemberDto getMemberByMemberCode(String memberCode) {
        log.info("Member code of the member:{}", memberCode);
        ZeusApiResponse<MemberDto> apiResponse = webClient.get()
                .uri(memberMgmtHost+"zeus/member/"+memberCode)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ZeusApiResponse<MemberDto>>() {})
                .block();
        return apiResponse.getResponse();
    }
}
