package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.folder.FolderFacade;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("folders")
@RequiredArgsConstructor
public class FolderController {

    private final FolderFacade folderFacade;

    @GetMapping("{id}")
    public FolderOutcomeDto getById(@PathVariable("id") Long id) {
        return folderFacade.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public FolderOutcomeDto create(@RequestBody @Valid FolderCreateDto dto) {
        return folderFacade.create(dto);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FolderOutcomeDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid FolderUpdateDto dto
    ) {
        return folderFacade.update(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        folderFacade.delete(id);
    }
}
