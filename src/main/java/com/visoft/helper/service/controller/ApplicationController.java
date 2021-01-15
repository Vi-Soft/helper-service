package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.application.ApplicationFacade;
import com.visoft.helper.service.service.application.tree.ApplicationTreeService;
import com.visoft.helper.service.transport.dto.application.ApplicationCopyDto;
import com.visoft.helper.service.transport.dto.application.ApplicationCreateDto;
import com.visoft.helper.service.transport.dto.application.ApplicationOutcomeDto;
import com.visoft.helper.service.transport.dto.application.ApplicationUpdateDto;
import com.visoft.helper.service.transport.dto.tree.ApplicationTreeOutcomeDto;
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
    private final ApplicationTreeService applicationTreeService;

    @GetMapping
    public List<ApplicationOutcomeDto> getAll() {
        return applicationFacade.getAll();
    }

    @GetMapping("{id}")
    public ApplicationOutcomeDto getById(@PathVariable("id") Long id) {
        return applicationFacade.getById(id);
    }

    @GetMapping("{id}/tree")
    public ApplicationTreeOutcomeDto getTree(@PathVariable("id") Long id) {
        return applicationTreeService.getTree(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApplicationOutcomeDto create(@RequestBody @Valid ApplicationCreateDto dto) {
        return applicationFacade.create(dto);
    }

    @PostMapping("copy/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void copy(@PathVariable("id") Long id, ApplicationCopyDto dto) {
        applicationFacade.copy(id, dto);
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
