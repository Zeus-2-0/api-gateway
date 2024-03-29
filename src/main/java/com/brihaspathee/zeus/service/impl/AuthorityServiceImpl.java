package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.repository.AuthorityRepository;
import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.exception.AuthorityNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.zeus.security.model.AuthorityDto;
import com.brihaspathee.zeus.security.model.AuthorityList;
import com.brihaspathee.zeus.service.interfaces.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, July 2022
 * Time: 1:25 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    /**
     * Repository to perform CRUD operations
     */
    private final AuthorityRepository authorityRepository;

    /**
     * Mapper to convert entity object from dto and vice versa
     */
    private final AuthorityMapper authorityMapper;

    /**
     * Get all the permissions in the system
     * @return
     */
    @Override
    public AuthorityList getAllAuthorities() {
        return AuthorityList.builder()
                .authorityDtos(authorityMapper.authoritiesToAuthorityDtos(authorityRepository.findAll()))
                .build();
    }

    /**
     * Get authority by authority id
     * @param authorityId
     * @return
     */
    @Override
    public AuthorityList getAuthorityById(UUID authorityId) {
        AuthorityDto authorityDto = authorityMapper.authorityToAuthorityDto(authorityRepository.findById(authorityId).orElseThrow( () ->
                {
                    throw new AuthorityNotFoundException("Permission with authority id " + authorityId + " not found");
                }));
        return AuthorityList.builder()
                .authorityDtos(Arrays.asList(authorityDto))
                .build();
    }

    /**
     * Get permission by authority name
     * @param permission
     * @return
     */
    @Override
    public AuthorityList getAuthorityByName(String permission) {
        AuthorityDto authorityDto = authorityMapper.authorityToAuthorityDto(authorityRepository.findAuthorityByPermission(permission).orElseThrow( () ->
        {
            throw new AuthorityNotFoundException("Permission " + permission + " not found");
        }));
        return AuthorityList.builder()
                .authorityDtos(Arrays.asList(authorityDto))
                .build();
    }

    /**
     * Create a new authority
     * @return
     */
    @Override
    public AuthorityDto saveAuthority(AuthorityDto authorityDto) {
        Authority authority = authorityRepository.save(authorityMapper.authorityDtoToAuthority(authorityDto));
        return authorityMapper.authorityToAuthorityDto(authority);
    }
}
