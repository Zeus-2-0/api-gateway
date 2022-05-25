package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.AccountService;
import com.brihaspathee.zeus.web.model.AccountDto;
import com.brihaspathee.zeus.web.resource.interfaces.AccountAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, May 2022
 * Time: 5:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@RestController
@RequiredArgsConstructor
public class AccountAPIImpl implements AccountAPI {

    private final AccountService accountService;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
