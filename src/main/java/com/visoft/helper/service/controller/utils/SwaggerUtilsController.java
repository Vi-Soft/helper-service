package com.visoft.helper.service.controller.utils;

import com.visoft.helper.service.transport.dto.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("swagger-utils")
@RestController
public class SwaggerUtilsController {

    /**
     * This method exists just to auto-generate swagger's api-docs
     */
    @PostMapping("/enums")
    public SwaggerEnumModer swaggerEnumModer(@RequestBody SwaggerEnumModer swaggerEnumModer) {
        return swaggerEnumModer;
    }

    @Getter
    @AllArgsConstructor
    public static class SwaggerEnumModer {
        private final Language enum_Language;
    }
}
