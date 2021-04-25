package com.kgc.sauw.UI.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kgc.sauw.UI.InterfaceElement;
import com.kgc.sauw.utils.Camera2D;

public class Image extends InterfaceElement {
    private Texture texture;
    private TextureRegion textureRegion;

    public Image(int x, int y, int w, int h) {
        setPosition(x, y);
        setSize(w, h);
    }

    public void setImg(Texture t) {
        this.texture = t;
    }

    public void setImg(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void render(SpriteBatch batch, Camera2D cam) {
        super.render(batch, cam);
        if (texture != null) batch.draw(texture, X + cam.X, Y + cam.Y, width, height);
        if (textureRegion != null) batch.draw(textureRegion, X + cam.X, Y + cam.Y, width, height);
    }
}
