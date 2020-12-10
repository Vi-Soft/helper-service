package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.folder.FolderFacade;
import com.visoft.helper.service.transport.dto.folder.FolderCreateDto;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("folders")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
public class FolderController {

    private final FolderFacade folderFacade;

    @PostMapping
    public FolderOutcomeDto create(@RequestBody @Valid FolderCreateDto dto) {
        return folderFacade.create(dto);
    }
}
