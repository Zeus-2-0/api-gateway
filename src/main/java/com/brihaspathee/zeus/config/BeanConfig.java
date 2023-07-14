package com.brihaspathee.zeus.config;

import com.brihaspathee.zeus.auth.ZeusUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 06, March 2023
 * Time: 11:58 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final ZeusUserDetailsService zeusUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(zeusUserDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:9090");
//            }
//        };
//    }
}
