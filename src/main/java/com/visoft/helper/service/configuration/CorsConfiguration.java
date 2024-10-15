package com.visoft.helper.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * + allow all Origins<p>
     * + allow all Headers<p>
     * + allow all Methods<p>
     * + exposed specified headers
     * + set max age to 30 minutes<p>
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://visoftapp1.visoft-eng.com:4141",
                    "http://visoftapp1.visoft-eng.com:3000",
                    "http://localhost:4141",
                    "http://localhost:3002"
                ).allowedMethods(allowedMethods())
                .allowedHeaders(allowedHeaders())
                .exposedHeaders(exposedHeaders());
    }

    /**
     * @return allowed methods as array of strings
     */
    private String[] allowedMethods() {
        return Stream.of(HttpMethod.values())
                .map(HttpMethod::toString)
                .toArray(String[]::new);
    }

    /**
     * @return special value as asterisk sign specified all headers
     */
    private String allowedHeaders() {
        return "*";
    }

    /**
     * @return a string of exposed headers as values delimited by a comma sign
     */
    private String exposedHeaders() {
        return String.join(
                ", ",
                new String[]{
                        "Content-Length",
                        "Content-Disposition"
                });
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }
}
