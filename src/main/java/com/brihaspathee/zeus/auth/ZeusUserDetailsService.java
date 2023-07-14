package com.brihaspathee.zeus.auth;

import com.brihaspathee.zeus.domain.repository.UserRepository;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 6:11 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.auth
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ZeusUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting Details from DB using JPA");
        User user = userRepository.findUserByUsername(username).orElseThrow( () -> {
            log.info("Inside the exception for user not found");
            return new UserNotFoundException("User with username " + username + " not found");
        }) ;
        log.info("user logged in is:{}", user.getUsername());
        return user;
    }
}
