package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.model.SimpleAuthUser;
import ru.kpfu.itis.repository.SimpleAuthUserRepository;
import ru.kpfu.itis.service.SimpleAuthUserService;
import ru.kpfu.itis.utils.UserRegistrationFormToUserTransformer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by Юлия on 05.09.2016.
 */

@Service
public class SimpleAuthUserServiceImpl implements SimpleAuthUserService {

    @Autowired
    SimpleAuthUserRepository userRepository;

    private AuthenticationManager authenticationManager;

    @Override
    public void saveNewUser(UserRegistrationForm form) {
        SimpleAuthUser user = UserRegistrationFormToUserTransformer.transform(form);
        userRepository.save(user);
    }

}
