package com.visoft.helper.service.facade.authorization;

import com.visoft.helper.service.transport.dto.authorization.LoginDto;

public interface AuthorizationFacade {

    String login(LoginDto dto);

    void logout();

}
