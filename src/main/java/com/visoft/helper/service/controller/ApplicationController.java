package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationFacade applicationFacade;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApplicationOutcomeDto create(@RequestBody @Valid ApplicationCreateDto dto) {
        return applicationFacade.create(dto);
    }
}
