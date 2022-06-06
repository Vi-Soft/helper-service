package com.visoft.helper.service.service.application.tree;

import com.visoft.helper.service.persistance.entity.Application;
import com.visoft.helper.service.persistance.entity.Folder;
import com.visoft.helper.service.service.application.ApplicationService;
import com.visoft.helper.service.transport.dto.tree.ApplicationTreeOutcomeDto;
import com.visoft.helper.service.transport.dto.tree.TreeContentDto;
import com.visoft.helper.service.transport.mapper.application.ApplicationTreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationTreeServiceImpl implements ApplicationTreeService {

    private final ApplicationService applicationService;
    private final ApplicationTreeMapper applicationTreeMapper;

    @Override
    public ApplicationTreeOutcomeDto getTree(Long id) {
        Application application = applicationService.findByIdUnsafe(id);
        ApplicationTreeOutcomeDto dto = applicationTreeMapper.toDto(application);
        buildApplicationTree(application, dto);
        return dto;
    }

    private void buildApplicationTree(Application application, ApplicationTreeOutcomeDto dto) {
        setFolderContent(application.getRootFolders(), dto);
    }

    private void buildFolderContent(Folder folder, TreeContentDto dto) {
        setFolderContent(folder.getChildren(), dto);
        setFileContent(folder, dto);
    }

    private void setFolderContent(List<Folder> folders, ApplicationTreeOutcomeDto dto) {
        folders.forEach(
                folder -> {
                    TreeContentDto treeContentDto = applicationTreeMapper.toDto(folder);
                    dto.getContent().add(treeContentDto);
                    buildFolderContent(folder, treeContentDto);
                }
        );
        sortByOrderNumber(dto.getContent());
    }

    private void setFileContent(Folder folder, TreeContentDto dto) {
        folder.getFiles().forEach(file ->
                dto.getContent().add(
                        applicationTreeMapper.toDto(file)
                )
        );
        sortByOrderNumber(dto.getContent());
    }

    private void sortByOrderNumber(List<TreeContentDto> treeContentDtos) {
        treeContentDtos.sort(Comparator.comparing(TreeContentDto::getOrderNumber));
    }
}
