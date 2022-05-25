package com.brihaspathee.zeus.web.model;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, May 2022
 * Time: 5:40 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String accountId;
    private String lineOfBusiness;
    private String marketplaceType;
    private String state;
    private String issuerSubscriberId;

    @Override
    public String toString() {
        return "AccountDto{" +
                "accountId='" + accountId + '\'' +
                ", lineOfBusiness='" + lineOfBusiness + '\'' +
                ", marketplaceType='" + marketplaceType + '\'' +
                ", state='" + state + '\'' +
                ", issuerSubscriberId='" + issuerSubscriberId + '\'' +
                '}';
    }
}
