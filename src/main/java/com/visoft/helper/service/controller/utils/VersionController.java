package com.visoft.helper.service.controller.utils;

import com.visoft.helper.service.service.utils.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("version")
@RequiredArgsConstructor
public class VersionController {

    private final VersionService versionService;

    @CrossOrigin
    @GetMapping
    public String version(){
        return versionService.getApplicationVersion();
    }
}
