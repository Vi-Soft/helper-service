package com.visoft.helper.service.facade.user;

import com.visoft.helper.service.persistance.entity.user.User;
import com.visoft.helper.service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public Optional<User> findByLogin(String login) {
        return userService.findByLogin(login);

    }

    @Override
    public Optional<User> findById(Long id) {
        return userService.findById(id);
    }
}
