package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.VKAuthUser;
import ru.kpfu.itis.model.enums.AuthorityType;
import ru.kpfu.itis.repository.VKAuthUserRepository;
import ru.kpfu.itis.service.VkUserService;

/**
 * Created by Юлия on 07.09.2016.
 */
@Service
public class VkUserServiceImpl implements VkUserService {

    @Autowired
    VKAuthUserRepository vkRepository;

    @Override
    public void saveNewUser(String accessToken, String uid, String firstName, String lastName) {
        VKAuthUser user = new VKAuthUser();
        user.setAccessToken(accessToken);
        user.setUsername(uid);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setType(AuthorityType.VK);
        vkRepository.save(user);
    }
}
