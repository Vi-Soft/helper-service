package com.visoft.helper.service.configuration.property;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(
        value = {
                "classpath:storage.yml",
        },
        factory = YamlPropertySourceFactory.class
)
@Configuration
public class PropertySourceConfiguration {
}
