package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.AccountList;
import com.brihaspathee.zeus.web.model.MemberDto;

import java.util.Map;

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

    /**
     * Get all the accounts in the system
     * @return
     */
    AccountList getAllAccounts();

    /**
     * Get all the accounts using the search parameters
     * @param searchParams
     * @return
     */
    AccountList getAccountsByParams(Map<String, String> searchParams);

    /**
     * Get account by account number
     * @param accountNumber
     * @return
     */
    AccountList getAccountByAccountNumber(String accountNumber);

    /**
     * Get member by member code
     * @param memberCode
     * @return
     */
    MemberDto getMemberByMemberCode(String memberCode);
}
