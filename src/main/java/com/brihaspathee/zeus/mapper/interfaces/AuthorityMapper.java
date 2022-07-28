package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.security.model.AuthorityDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 1:30 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthorityMapper {

    /**
     * Converts the authority dto to authority entity
     * @param authorityDto
     * @return
     */
    Authority authorityDtoToAuthority(AuthorityDto authorityDto);

    /**
     * Converts the authority entity to authority dto
     * @param authority
     * @return
     */
    AuthorityDto authorityToAuthorityDto(Authority authority);

    /**
     * Converts the set of authority dtos to authority entities
     * @param authorityDtos
     * @return
     */
    List<Authority> authorityDtosToAuthorities(List<AuthorityDto> authorityDtos);

    /**
     * Converts the set of authority entities to authority dtos
     * @param authorities
     * @return
     */
    List<AuthorityDto> authoritiesToAuthorityDtos(List<Authority> authorities);
}
