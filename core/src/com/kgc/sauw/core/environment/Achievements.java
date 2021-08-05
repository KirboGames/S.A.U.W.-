package com.kgc.sauw.core.environment;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.kgc.sauw.core.utils.languages.Languages;
import com.kgc.sauw.game.gui.HUD;
import com.kgc.sauw.core.config.Settings;
import com.kgc.sauw.core.entity.Player;
import org.json.JSONArray;

import static com.kgc.sauw.resource.Files.playerData;
import static com.kgc.sauw.resource.Files.saveData;

public class Achievements {
    public static class achievement {
        public String id;
        public String title;
        public String text;
        public Texture img;
        public int giveCoins;
        public boolean wasGave = false;

        public achievement(String id, String title, String text, Texture img, int giveCoins) {
            this.id = id;
            this.title = title;
            this.text = text;
            this.img = img;
            this.giveCoins = giveCoins;
        }
    }

    public Achievements() {
        try {
            JSONArray achievements = new JSONArray(Gdx.files.internal("json/achivements.json").readString());
            for (int i = 0; i < achievements.length(); i++) {
                addAchievement(new achievement(achievements.getJSONObject(i).getString("id"),
                        Languages.getString(achievements.getJSONObject(i).getString("title")),
                        Languages.getString(achievements.getJSONObject(i).getString("txt")),
                        new Texture(Gdx.files.internal(achievements.getJSONObject(i).getString("texture"))),
                        achievements.getJSONObject(i).getInt("giveCoins")));
            }
        } catch (Exception e) {

        }
    }

    private ArrayList<achievement> achievements = new ArrayList<achievement>();

    public void addAchievement(achievement ac) {
        achievements.add(ac);
    }

    public void giveAchievment(Player pl, String id, HUD GI, Settings s) {
        if (!s.useConsole) {
            for (achievement a : achievements) {
                if (a.id.equals(id)) {
                    if (!a.wasGave) {
                        try {
                            int WIDTH = Gdx.graphics.getWidth();
                            int HEIGHT = Gdx.graphics.getHeight();
                            playerData.put("SAUW_Coins", playerData.getInt("SAUW_Coins") + a.giveCoins);
                            saveData();
                            a.wasGave = true;
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
    }
}
