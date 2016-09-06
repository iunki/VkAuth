package ru.kpfu.itis.service;

/**
 * Created by Юлия on 07.09.2016.
 */
public interface VkUserService {

    public void saveNewUser(String accessToken, String uid, String firstName, String lastName);
}
