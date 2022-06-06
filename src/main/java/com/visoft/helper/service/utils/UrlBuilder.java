package com.visoft.helper.service.utils;

import com.visoft.helper.service.transport.dto.Language;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Setter(onMethod_ = @Autowired)
@Component
public class UrlBuilder {

    private DirStructureCreatorProperties dirStructureCreatorProperties;

    public String generate(Language language, String fileNameWithExtension) {
        return
                dirStructureCreatorProperties.appResourcesHostAndPort() + File.separator +
                dirStructureCreatorProperties.getApiFolder() + File.separator +
                language.getDescription() + File.separator +
                fileNameWithExtension;
    }
}
