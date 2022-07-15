package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.zeus.web.model.AuthorityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 2:06 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorityMapperImpl implements AuthorityMapper {

    /**
     * Converts the authority dto to authority entity
     * @param authorityDto
     * @return
     */
    @Override
    public Authority authorityDtoToAuthority(AuthorityDto authorityDto) {
        if(authorityDto == null){
            return null;
        }

        Authority authority = Authority.builder()
                .authorityId(authorityDto.getAuthorityId())
                .permission(authorityDto.getPermission())
                .createdDate(LocalDateTime.now())
                .build();
        return authority;
    }

    /**
     * Converts the authority entity to authority dto
     * @param authority
     * @return
     */
    @Override
    public AuthorityDto authorityToAuthorityDto(Authority authority) {
        if(authority == null){
            return null;
        }

        AuthorityDto authorityDto = AuthorityDto.builder()
                .authorityId(authority.getAuthorityId())
                .permission(authority.getPermission())
                .build();
        return authorityDto;
    }

    /**
     * Converts the set of authority dtos to authority entities
     * @param authorityDtos
     * @return
     */
    @Override
    public List<Authority> authorityDtosToAuthorities(List<AuthorityDto> authorityDtos) {
        return authorityDtos.stream().map(this::authorityDtoToAuthority).collect(Collectors.toList());
    }

    /**
     * Converts the set of authority entities to authority dtos
     * @param authorities
     * @return
     */
    @Override
    public List<AuthorityDto> authoritiesToAuthorityDtos(List<Authority> authorities) {
        return authorities.stream().map(this::authorityToAuthorityDto).collect(Collectors.toList());
    }
}
