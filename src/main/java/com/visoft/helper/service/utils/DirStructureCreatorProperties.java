package com.visoft.helper.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class DirStructureCreatorProperties {

    @Value("${app.local-folder}")
    private String userHomeDirectory;

    @Value("${app.prod-folder}")
    private String prodHomeDirectory;

    @Value("${app.local-resources-folder}")
    private String localResourceFolder;

    @Value("${app.api-folder}")
    private String apiFolder;

    @Value("${app.enable-prod-folder}")
    private Boolean enableProdFolder;


    @Value("${app.host}")
    private String host;

    @Value("${app.port}")
    private int port;

    public String appResourcesPath() {
        return Paths.get(getLocalOrProdResources(), localResourcesFolder()).toString();
    }

    public String appResourcesHostAndPort() {
        return String.format("%s:%d", host, port);
    }

    public String getApiFolder() {
        return apiFolder;
    }

    private String localResourcesFolder() {
        return Paths.get(localResourceFolder, getApiFolder()).toString();
    }

    private String userHomeDirectory() {
        return userHomeDirectory;
    }

    private String prodHomeDirectory() {
        return prodHomeDirectory;
    }

    private Boolean enableProdFolder(){
        return enableProdFolder;
    }

    private String getLocalOrProdResources() {
        return enableProdFolder() ? prodHomeDirectory() : userHomeDirectory();
    }
}
