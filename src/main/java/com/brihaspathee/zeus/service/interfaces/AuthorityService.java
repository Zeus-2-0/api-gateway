package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.security.model.AuthorityDto;
import com.brihaspathee.zeus.security.model.AuthorityList;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 1:24 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthorityService {

    /**
     * Get all the permissions that are present in the system
     * @return
     */
    AuthorityList getAllAuthorities();

    /**
     * Get permission by authority id
     * @param authorityId
     * @return
     */
    AuthorityList getAuthorityById(UUID authorityId);

    /**
     * Get permission by authority name
     * @param permission
     * @return
     */
    AuthorityList getAuthorityByName(String permission);

    /**
     * Create a new permission
     * @param authorityDto
     * @return
     */
    AuthorityDto saveAuthority(AuthorityDto authorityDto);
}
