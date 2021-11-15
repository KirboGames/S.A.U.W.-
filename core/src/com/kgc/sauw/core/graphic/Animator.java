package com.kgc.sauw.core.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

import static com.kgc.sauw.core.GameContext.SAUW;

public class Animator {
    public static class AnimationRegion {
        protected final TextureRegion[] frames;

        public AnimationRegion(Texture animationTexture, int xFramesCount, int yFramesCount) {
            TextureRegion[][] tmp = TextureRegion.split(animationTexture, animationTexture.getWidth() / xFramesCount, animationTexture.getHeight() / yFramesCount);
            frames = new TextureRegion[xFramesCount * yFramesCount];
            int index = 0;
            for (int i = 0; i < yFramesCount; i++) {
                for (int j = 0; j < xFramesCount; j++) {
                    frames[index++] = tmp[i][j];
                }
            }
        }
    }

    private final HashMap<Integer, Animation<TextureRegion>> animations = new HashMap<>();
    private final HashMap<Integer, AnimationRegion> animationRegionHashMap = new HashMap<>();
    private static float stateTime = 0f;

    public Animator() {

    }

    public TextureRegion[] getFrames(String id) {
        return animationRegionHashMap.get(SAUW.getId(id)).frames;
    }

    public void addAnimationRegion(String id, Texture animationTexture, int xFramesCount, int yFramesCount) {
        animationRegionHashMap.put(SAUW.registeredId(id), new AnimationRegion(animationTexture, xFramesCount, yFramesCount));
    }

    public void addAnimation(String id, String animationRegionId, float frameDuration, int... frameNumbers) {
        TextureRegion[] frames = new TextureRegion[frameNumbers.length];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = animationRegionHashMap.get(SAUW.getId(animationRegionId)).frames[frameNumbers[i]];
        }
        animations.put(SAUW.registeredId(id), new Animation<>(frameDuration, frames));
    }

    public static void update() {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrame(String id) {
        return animations.get(SAUW.getId(id)).getKeyFrame(stateTime, true);
    }
}