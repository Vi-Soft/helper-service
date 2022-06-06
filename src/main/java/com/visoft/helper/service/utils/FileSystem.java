package com.visoft.helper.service.utils;

import com.visoft.helper.service.transport.dto.Language;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;

@Setter(onMethod_ = @Autowired)
@Component
public class FileSystem {

    private static final String NON_NULL_REQUIREMENT = "File name with extension must not be null";
    private static final char DOT_SYMBOL = '.';
    private static final String FILE_NAME_FORMAT = "%s-%s%s";

    private DirStructureCreatorProperties dirStructureCreatorProperties;

    @SneakyThrows
    public String write(Language language, MultipartFile file) {
        return write(language, file.getBytes(), calculateFileName(file.getOriginalFilename()));
    }

    public List<String> list(Language language) {
        return
                Stream.of(
                        Objects.requireNonNull(
                                new File(mkdirIfDoesntExists(language)).listFiles())
                )
                        .filter(file -> !file.isDirectory())
                        .map(File::getName)
                        .collect(Collectors.toList());
    }

    @SneakyThrows
    private String write(Language language, byte[] data, String fileName) {
        String path = mkdirIfDoesntExists(language);
        final FileOutputStream output = new FileOutputStream(Paths.get(path, fileName).toFile());
        IOUtils.write(data, output);
        return fileName;
    }

    private String calculateFileName(String fileName) {
        Objects.requireNonNull(fileName, NON_NULL_REQUIREMENT);
        int index = fileName.lastIndexOf(DOT_SYMBOL);
        return
                String.format(
                        FILE_NAME_FORMAT,
                        fileName.substring(0, index),
                        DateTimeUtil.asDateAndTime(),
                        fileName.substring(index)
                );
    }

    @PostConstruct
    public void mkdirIfDoesntExists() {
        Arrays.stream(Language.values()).sequential().forEach(this::mkdirIfDoesntExists);
    }

    @SneakyThrows
    private String mkdirIfDoesntExists(Language language) {
        final Path path = Paths.get(dirStructureCreatorProperties.appResourcesPath(), language.getDescription());
        if (!path.toFile().exists()) {
            Files.createDirectories(path);
        }
        return path.toString();
    }
}
