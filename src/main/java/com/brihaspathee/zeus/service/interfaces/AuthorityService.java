package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.AuthorityDto;
import com.brihaspathee.zeus.web.model.AuthorityList;

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
     * Create a new permission
     * @param authorityDto
     * @return
     */
    AuthorityDto createAuthority(AuthorityDto authorityDto);
}
