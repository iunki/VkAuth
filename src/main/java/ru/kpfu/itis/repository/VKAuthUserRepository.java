package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.VKAuthUser;
import ru.kpfu.itis.model.enums.AuthorityType;

/**
 * Created by Юлия on 02.09.2016.
 */
@Repository
public interface VKAuthUserRepository extends JpaRepository<VKAuthUser, String> {

    VKAuthUser findOneByUsernameAndType(String identificationName, AuthorityType type);
}
