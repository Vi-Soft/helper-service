package com.visoft.helper.service.utils;

import com.visoft.helper.service.transport.dto.Language;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Setter(onMethod_ = @Autowired)
@Component
public class UrlBuilder {

    private static final String PATH_FORMAT = "/%s/%s/%s";

    private DirStructureCreatorProperties dirStructureCreatorProperties;

    @SneakyThrows
    public String generate(Language language, String fileNameWithExtension) {
        return
                new URL(
                        dirStructureCreatorProperties.appResourcesProtocol(),
                        dirStructureCreatorProperties.appResourcesHost(),
                        dirStructureCreatorProperties.appResourcesPort(),
                        String.format(
                                PATH_FORMAT,
                                dirStructureCreatorProperties.getApiFolder(),
                                language.getDescription(),
                                fileNameWithExtension
                        )
                ).toString();
    }
}
