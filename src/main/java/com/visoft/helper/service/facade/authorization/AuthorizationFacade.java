package com.visoft.helper.service.facade.authorization;

import com.visoft.helper.service.transport.dto.authorization.LoginDto;
import com.visoft.helper.service.transport.dto.authorization.LoginOutcomeDto;

public interface AuthorizationFacade {

    LoginOutcomeDto login(LoginDto dto);

    void logout();

}
