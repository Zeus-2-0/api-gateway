package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.auth.ZeusUserDetailsService;
import com.brihaspathee.zeus.auth.util.ZeusJwtUtil;
import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.service.interfaces.APIGatewayUserService;
import com.brihaspathee.zeus.web.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, May 2022
 * Time: 5:40 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class APIGatewayUserServiceImpl implements APIGatewayUserService {

    private final ZeusUserDetailsService zeusUserDetailsService;

    private final ZeusJwtUtil zeusJwtUtil;


    @Override
    public AuthenticationResponse getAuthenticationResponse(String username) {
        final User userDetails = zeusUserDetailsService.loadUserByUsername(username);
        return getAuthenticationResponse(userDetails);
    }

    @Override
    public AuthenticationResponse getAuthenticationResponse(User user) {
        final String jwt = zeusJwtUtil.generateToken(user);
        Date expirationDate = zeusJwtUtil.extractExpiration(jwt);
        LocalDateTime expirationTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return AuthenticationResponse.builder()
                .authToken(jwt)
                .authExpiration(expirationTime)
                .userDto(getUserDto(user))
                .isAuthenticated(true)
                .authMessage("Authentication Successful")
                .build();
    }

    private UserDto getUserDto(User user){
        UserDto userDto = UserDto.builder()
                .username(user.getUsername())
                .roleDtos(new ArrayList<>())
                //.password(user.getPassword())
                .build();
        Set<Role> roles = user.getRoles();
        roles.stream().forEach(role -> {
            RoleDto roleDto = RoleDto.builder()
                    .roleName(role.getRoleName())
                    .authorityDtos(new ArrayList<>())
                    .build();
            Set<Authority> authorities = role.getAuthorities();
            authorities.stream().forEach(authority -> {
                AuthorityDto authorityDto = AuthorityDto.builder()
                        .permission(authority.getPermission())
                        .build();
                roleDto.getAuthorityDtos().add(authorityDto);
            });
            userDto.getRoleDtos().add(roleDto);
        });
        return userDto;
    }
}
