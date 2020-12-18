package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationFacade applicationFacade;

    @GetMapping
    public List<ApplicationOutcomeDto> getAll() {
        return applicationFacade.getAll();
    }

    @GetMapping("{id}")
    public ApplicationOutcomeDto getById(@PathVariable("id") Long id) {
        return applicationFacade.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApplicationOutcomeDto create(@RequestBody @Valid ApplicationCreateDto dto) {
        return applicationFacade.create(dto);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApplicationOutcomeDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid ApplicationUpdateDto dto
    ) {
        return applicationFacade.update(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        applicationFacade.delete(id);
    }
}
