package app.text_quest.database.repo;

import app.text_quest.database.model.user.types.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    BasicUser findByMail(String mail);
}
