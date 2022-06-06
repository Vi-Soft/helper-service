package com.visoft.helper.service.controller;

import com.visoft.helper.service.facade.file.FileFacade;
import com.visoft.helper.service.transport.dto.Language;
import com.visoft.helper.service.transport.dto.file.FileCreateDto;
import com.visoft.helper.service.transport.dto.file.FileDto;
import com.visoft.helper.service.transport.dto.file.FileOutcomeDto;
import com.visoft.helper.service.transport.dto.file.FileUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {

    private final FileFacade fileFacade;

    @GetMapping("{id}")
    public FileOutcomeDto getById(@PathVariable("id") Long id) {
        return fileFacade.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public FileOutcomeDto create(@RequestBody @Valid FileCreateDto dto) {
        return fileFacade.create(dto);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FileOutcomeDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid FileUpdateDto dto
    ) {
        return fileFacade.update(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        fileFacade.delete(id);
    }

    @PostMapping("{language}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String upload(@PathVariable Language language,
                         MultipartFile file) {
        return fileFacade.upload(language, file);
    }

    @GetMapping("{language}/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FileDto> list(@PathVariable Language language) {
        return fileFacade.list(language);
    }
}
