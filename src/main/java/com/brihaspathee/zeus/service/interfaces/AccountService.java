package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, May 2022
 * Time: 5:43 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AccountService {

    List<AccountDto> getAllAccounts();
}
