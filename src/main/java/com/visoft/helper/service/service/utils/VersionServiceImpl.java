package com.visoft.helper.service.service.utils;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class VersionServiceImpl implements VersionService {

    @Override
    public String getApplicationVersion() {
        Properties properties = new Properties();
        String fileName = "version.properties";

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty("application.version");
            } else {
                throw new FileNotFoundException("Property file '" + fileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return "undefined";
    }
}
