package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.web.model.AuthenticationDto;
import com.brihaspathee.zeus.web.model.AuthenticationRequest;
import com.brihaspathee.zeus.web.model.AuthenticationResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 4:41 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/zeus")
@CrossOrigin(origins = "http://localhost:7200")
@Validated
public interface AuthenticationAPI {

    @PostMapping("/jwt/authenticate")
    AuthenticationResponse jwtAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception;

    @GetMapping("/basic/authenticate")
    AuthenticationResponse basicAuthentication(@AuthenticationPrincipal User user) throws Exception;
}
