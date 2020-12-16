package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.folder.FolderFacade;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("folders")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
public class FolderController {

    private final FolderFacade folderFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderOutcomeDto create(@RequestBody @Valid FolderCreateDto dto) {
        return folderFacade.create(dto);
    }

    @GetMapping("{id}")
    public FolderOutcomeDto getById(@PathVariable("id") Long id) {
        return folderFacade.getById(id);
    }

    @PutMapping("{id}")
    public FolderOutcomeDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid FolderUpdateDto dto
    ) {
        return folderFacade.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        folderFacade.delete(id);
    }
}
