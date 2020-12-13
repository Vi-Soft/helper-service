package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.file.FileFacade;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileOutcomeDto create(@RequestBody @Valid FileCreateDto dto){
        return  fileFacade.create(dto);
    }
}
