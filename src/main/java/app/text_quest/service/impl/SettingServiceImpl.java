package app.text_quest.service.impl;

import app.text_quest.model.Setting;
import app.text_quest.model.User;
import app.text_quest.repo.SettingRepository;
import app.text_quest.service.SettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Deprecated
@Service
@Transactional
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public Setting addSetting(Setting setting) {
        return this.settingRepository.saveAndFlush(setting);
    }

    @Override
    public void delete(Setting setting) {
        this.settingRepository.delete(setting);
    }

    @Override
    public Setting getByUser(User user) {
        return this.settingRepository.findByUserId(user.getId());
    }

    @Override
    public Setting editSetting(Setting setting) {
        return this.settingRepository.saveAndFlush(setting);
    }

    @Override
    public List<Setting> getAll() {
        return this.settingRepository.findAll();
    }
}
