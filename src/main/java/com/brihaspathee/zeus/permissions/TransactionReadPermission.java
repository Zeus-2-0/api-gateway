package com.brihaspathee.zeus.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 6:20 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.permissions
 * To change this template use File | Settings | File and Code Template
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('transaction.read')")
public @interface TransactionReadPermission {
}
