package com.visoft.helper.service.service.application.tree;

import com.visoft.helper.service.transport.dto.tree.ApplicationTreeOutcomeDto;

public interface ApplicationTreeService {

    ApplicationTreeOutcomeDto getTree(Long id);
}
