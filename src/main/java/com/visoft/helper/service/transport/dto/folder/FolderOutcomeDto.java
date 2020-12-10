package com.visoft.helper.service.transport.dto.folder;

import com.visoft.helper.service.transport.dto.IdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FolderOutcomeDto extends IdDto {

    private String name;
    private long fileOrder;
    private boolean root;
    private Long parentId;
    private Long applicationId;
    private Set<Long> childrenIds;
    private Set<Long> fileIds;
}
