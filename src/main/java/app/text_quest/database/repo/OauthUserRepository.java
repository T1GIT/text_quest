package app.text_quest.database.repo;

import app.text_quest.database.model.user.types.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {

    OauthUser findByOauthId(String oauthId);
}
