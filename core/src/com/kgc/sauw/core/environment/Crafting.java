package com.kgc.sauw.core.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import static com.kgc.sauw.core.GameContext.SAUW;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Crafting {
    public static class Craft {
        public int[] result;
        public int[][] ingredients;

        public Craft(int[] result, int[][] ingredients) {
            this.result = result;
            this.ingredients = ingredients;
        }
    }

    public Crafting() {
        addCraftsFromDirectory(Gdx.files.internal("json/Crafts"));
    }

    public ArrayList<Craft> crafts = new ArrayList<Craft>();

    public void addCraft(Craft craft) {
        crafts.add(craft);
    }

    public void addCraftsFromDirectory(FileHandle dir) {
        FileHandle includesFile = dir.child("crafts.includes");
        String[] fileNames = includesFile.readString().split("\\r\\n");
        for (String fileName : fileNames) {
            addCraftsFromFile(dir.child(fileName));
        }
    }

    public void addCraftsFromFile(FileHandle file) {
        try {
            JSONArray crafts = new JSONArray(file.readString());
            for (int i = 0; i < crafts.length(); i++) {
                int[] result;
                int[][] ingr;
                JSONObject craft = crafts.getJSONObject(i);
                JSONObject resultA = craft.getJSONObject("result");
                JSONArray ingrA = craft.getJSONArray("ingredients");
                result = new int[3];
                ingr = new int[ingrA.length()][3];

                result[0] = SAUW.getId(resultA.getString("id"));
                result[1] = resultA.getInt("count");
                result[2] = resultA.getInt("data");
                for (int j = 0; j < ingrA.length(); j++) {
                    ingr[j][0] = SAUW.getId(ingrA.getJSONObject(j).getString("id"));
                    ingr[j][1] = ingrA.getJSONObject(j).getInt("count");
                    ingr[j][2] = ingrA.getJSONObject(j).getInt("data");
                }
                this.addCraft(new Craft(result, ingr));
            }
        } catch (Exception e) {
            Gdx.app.log("Mods error", "load craft error : " + e);
        }
    }
}