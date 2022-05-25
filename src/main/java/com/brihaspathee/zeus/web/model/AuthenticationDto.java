package com.brihaspathee.zeus.web.model;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 4:40 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {

    private String authMessage;
}
