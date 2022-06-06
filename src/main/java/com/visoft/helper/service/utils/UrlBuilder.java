package com.visoft.helper.service.utils;

import com.visoft.helper.service.transport.dto.Language;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Setter(onMethod_ = @Autowired)
@Component
public class UrlBuilder {

    private DirStructureCreatorProperties dirStructureCreatorProperties;

    public String generate(Language language, String fileNameWithExtension) {
        return
                Paths.get(
                        dirStructureCreatorProperties.appResourcesHostAndPort(),
                        dirStructureCreatorProperties.getApiFolder(),
                        language.getDescription(),
                        fileNameWithExtension
                ).toString();
    }
}
