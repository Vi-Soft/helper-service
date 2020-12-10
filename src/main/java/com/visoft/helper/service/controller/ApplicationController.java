package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("applications")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class ApplicationController {

    private final ApplicationFacade applicationFacade;

    @PostMapping

    public ApplicationOutcomeDto create(@RequestBody @Valid ApplicationCreateDto dto) {
        return applicationFacade.create(dto);
    }

    @PutMapping("{id}")
    public ApplicationOutcomeDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid ApplicationUpdateDto dto
    ) {
        return applicationFacade.update(id, dto);
    }

    @GetMapping
    public List<ApplicationOutcomeDto> getAll() {
        return applicationFacade.getAll();
    }
}
