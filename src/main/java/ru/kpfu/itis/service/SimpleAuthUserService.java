package ru.kpfu.itis.service;

import ru.kpfu.itis.form.UserRegistrationForm;

/**
 * Created by Юлия on 05.09.2016.
 */
public interface SimpleAuthUserService {

    void saveNewUser(UserRegistrationForm form);
}
