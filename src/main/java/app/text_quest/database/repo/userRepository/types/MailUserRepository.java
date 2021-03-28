package app.text_quest.database.repo.userRepository.types;

import app.text_quest.database.model.user.types.MailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 <h2> An interface {@link MailUserRepository}

 <p> Provides accessing to mail users in the database

 @see org.springframework.data.jpa.repository.JpaRepository */
@Repository
public interface MailUserRepository extends JpaRepository<MailUser, Long> {

    /**
     Searches email user by its oauth id

     @param mail email for searching
     @return mail user model or null if didn't found
     */
    MailUser findByMail(String mail);
}
