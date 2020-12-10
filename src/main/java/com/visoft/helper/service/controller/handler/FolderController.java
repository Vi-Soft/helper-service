package com.visoft.helper.service.controller.handler;

import com.visoft.helper.service.facade.folder.FolderFacade;
import com.visoft.helper.service.transport.dto.folder.FolderOutcomeDto;
import com.visoft.helper.service.transport.dto.folder.FolderRootCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("folders")
@RequiredArgsConstructor
public class FolderController {

    private final FolderFacade folderFacade;

    @PostMapping
    public FolderOutcomeDto createRoot(@RequestBody @Valid FolderRootCreateDto dto) {
        return folderFacade.createRoot(dto);
    }
}
