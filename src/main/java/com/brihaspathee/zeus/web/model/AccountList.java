package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, June 2022
 * Time: 6:30 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountList {

    private List<AccountDto> accountDtos;

    @Override
    public String toString() {
        return "AccountList{" +
                "accountDtos=" + accountDtos +
                '}';
    }
}
