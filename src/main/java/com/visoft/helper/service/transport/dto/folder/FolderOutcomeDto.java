package com.visoft.helper.service.transport.dto.folder;

import com.visoft.helper.service.transport.dto.OrderNumberIdDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FolderOutcomeDto extends OrderNumberIdDto {

    private Long parentId;
    private Long applicationId;
    private List<Long> childrenIds;
    private List<Long> fileIds;
}
