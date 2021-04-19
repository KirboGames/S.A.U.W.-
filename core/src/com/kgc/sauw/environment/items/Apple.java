package com.kgc.sauw.environment.items;

import com.kgc.sauw.environment.Items;

import static com.kgc.sauw.graphic.Graphic.TEXTURES;
import static com.kgc.sauw.utils.Languages.LANGUAGES;

public class Apple extends Item {
    public Apple() {
        super(2);
        this.t = TEXTURES.apple;

        ItemConfiguration.weight = 0.2f;
        ItemConfiguration.name = LANGUAGES.getString("apple");
        ItemConfiguration.type = Items.Type.FOOD;
        ItemConfiguration.foodScore = 4;
    }
}