package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.model.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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


    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accounts = Arrays.asList(
              AccountDto.builder()
                      .accountId("BE423SDFT24")
                      .lineOfBusiness("HIX")
                      .marketplaceType("FFM")
                      .state("FL")
                      .issuerSubscriberId("Z142345234")
                      .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT25")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("GA")
                        .issuerSubscriberId("Z142345235")
                        .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT26")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("SC")
                        .issuerSubscriberId("Z142345236")
                        .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT27")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("NC")
                        .issuerSubscriberId("Z142345237")
                        .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT28")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("NY")
                        .issuerSubscriberId("Z142345238")
                        .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT29")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("MA")
                        .issuerSubscriberId("Z142345239")
                        .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT30")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("NH")
                        .issuerSubscriberId("Z142345240")
                        .build(),
                AccountDto.builder()
                        .accountId("BE423SDFT31")
                        .lineOfBusiness("HIX")
                        .marketplaceType("FFM")
                        .state("CA")
                        .issuerSubscriberId("Z142345241")
                        .build()
        );
        return accounts;
    }
}
