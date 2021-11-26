package com.kgc.sauw.core.environment.achievements;

import com.badlogic.gdx.graphics.Texture;
import com.kgc.sauw.core.register.RegistryObject;
import com.kgc.sauw.core.utils.languages.Languages;

public class Achievement extends RegistryObject {
    public String title;
    public String description;
    public Texture icon;
    public int giveCoins;
    public AchievementChecker achievementChecker;

    public boolean check() {
        if (achievementChecker != null) return achievementChecker.check();
        return false;
    }

    @Override
    public void init() {
        title = Languages.getString("sauw.achievements." + stringId + ".title");
        description = Languages.getString("sauw.achievements." + stringId + ".description");
    }
}