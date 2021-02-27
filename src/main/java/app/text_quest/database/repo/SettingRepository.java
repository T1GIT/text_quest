package app.text_quest.database.repo;

import app.text_quest.database.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
    Setting findByUserId(long userId);
}
