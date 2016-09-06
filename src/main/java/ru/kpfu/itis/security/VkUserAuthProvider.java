package ru.kpfu.itis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.kpfu.itis.model.VKAuthUser;
import ru.kpfu.itis.model.enums.AuthorityType;
import ru.kpfu.itis.repository.VKAuthUserRepository;

/**
 * Created by Юлия on 06.09.2016.
 */
public class VkUserAuthProvider implements AuthenticationProvider {

    @Autowired
    VKAuthUserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VKAuthUser user = repository.findOneByUsernameAndType(authentication.getName(), AuthorityType.VK);
        return new VkTokenAuthentication(user, authentication.getDetails());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(VkTokenAuthentication.class);
    }
}
