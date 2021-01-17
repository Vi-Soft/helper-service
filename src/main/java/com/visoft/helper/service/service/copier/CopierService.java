package com.visoft.helper.service.service.copier;

import com.visoft.helper.service.transport.dto.application.ApplicationCopyDto;

public interface CopierService {

    void copyApplication(Long id, ApplicationCopyDto dto);
}
