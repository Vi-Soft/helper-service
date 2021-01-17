package com.visoft.helper.service.transport.dto.folder;

import com.visoft.helper.service.transport.dto.ordernumber.OrderNumberIdDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class FolderOutcomeDto extends OrderNumberIdDto {

    private Long parentId;

    @NotNull
    private Long applicationId;

    @NotNull
    private List<Long> childrenIds;

    @NotNull
    private List<Long> fileIds;
}
