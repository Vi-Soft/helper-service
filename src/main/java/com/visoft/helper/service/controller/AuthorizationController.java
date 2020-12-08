package com.visoft.helper.service.controller;


import com.visoft.helper.service.facade.authorization.AuthorizationFacade;
import com.visoft.helper.service.transport.dto.authorization.LoginDto;
import com.visoft.helper.service.transport.dto.authorization.LoginOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationFacade authorizationFacade;

    @PostMapping("login")
    public LoginOutcomeDto login(@Valid @RequestBody LoginDto dto) {
        return authorizationFacade.login(dto);
    }

    @PreAuthorize("authentication.principal!=null")
    @GetMapping("log-out")
    public void logout() {
        authorizationFacade.logout();
    }
}
