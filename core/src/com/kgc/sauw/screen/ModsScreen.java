package com.kgc.sauw.screen;

import com.kgc.sauw.Camera2D;
import com.kgc.sauw.InterfaceAPI.Button;
import com.kgc.sauw.InterfaceAPI.Checkbox;
import com.kgc.sauw.InterfaceAPI.InterfaceElement;
import com.kgc.sauw.InterfaceAPI.Slider;
import com.kgc.sauw.MainGame;
import com.kgc.sauw.Textures;
import com.kgc.sauw.screen.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONException;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ModsScreen implements Screen {
	private class Mod {
		public Texture modIcon;
		public String modName;
		public Mod(String modPath) {
			FileHandle manifestFile = Gdx.files.external(modPath + "/manifest.json");
			try {
				JSONObject manifeat = new JSONObject(manifestFile.readString());

				modName = Gdx.files.external(modPath).name();
				modIcon = new Texture(Gdx.files.external(modPath + "/" + manifeat.getString("icon")));
			}
			catch (Exception e) {
				Gdx.app.log("ModInfoLoadError", e.toString());
			}
		}
	}
	private ArrayList<Mod> Mods = new ArrayList<Mod>();
	private SpriteBatch batch;
	private Camera2D cam;
	private Textures t;
	private int width = Gdx.graphics.getWidth();
	private int height = Gdx.graphics.getHeight();

	private Button closeButton;
	private Slider slider;
	private ModInfo modInfo0;
	private ModInfo modInfo1;
	private ModInfo modInfo2;

	private Texture background1;

	public ModsScreen(final MainGame game, Textures t, final MenuScreen ms) {
		this.batch = ms.b;
		this.cam = ms.camera;
		this.t = t;
		background1 = Textures.generateTexture(13, height / (width / 16) - 1, false);
		closeButton = new Button("closeButton", width - width / 16, height - width / 16, width / 32, width / 32, t.closeButton, t.closeButton);
		closeButton.setEventListener(new Button.EventListener(){
				@Override
				public void onClick() {
					game.setScreen(ms);
				}
			});
		slider = new Slider(width / 16 * 13 + width / 64 * 3, width / 32, width / 32, height - width / 16);
		modInfo2 = new ModInfo(width / 16, width / 32 * 2);
		modInfo1 = new ModInfo(width / 16, width / 32 * 7);
		modInfo0 = new ModInfo(width / 16, width / 32 * 12);
		FileHandle modsFolder = Gdx.files.external("S.A.U.W./Mods");
		String[] mods = modsFolder.file().list();
		for (String mod : mods) {
			if (modsFolder.child(mod).isDirectory()) {
				if (modsFolder.child(mod).child("manifest.json").exists()) this.Mods.add(new Mod(modsFolder.child(mod).path()));
			}
		}
		setModsInfo(0);
		slider.setMaxValue((Mods.size() - 1) * 40);
		slider.setEventListener(new Slider.EventListener(){
				@Override
				public void onValueChange(int v1) {
					int v = (int)Math.floor(v1 / 40);
					setModsInfo(v);
				}
			});
		if (Mods.size() <= 3) slider.hide(true);
	}
	public void setModsInfo(int pos){
		modInfo0.hide(false);
		modInfo1.hide(false);
		modInfo2.hide(false);
		if(Mods.size() > 0) {
			modInfo0.setMod(Mods.get(pos));
			if (pos + 1 < Mods.size()) modInfo1.setMod(Mods.get(pos + 1));
			else modInfo1.hide(true);
			if (pos + 2 < Mods.size()) modInfo2.setMod(Mods.get(pos + 2));
			else modInfo2.hide(true);
		}
	}
	@Override
	public void render(float p1) {
		closeButton.update(cam);
		slider.update(cam);
		batch.begin();
		batch.draw(t.standartBackground_full, cam.X, cam.Y, width, height);
		batch.draw(background1, cam.X + width / 32, cam.Y + width / 32, width / 16 * 13, height - (width / 16));
		closeButton.render(batch, cam);
		modInfo0.render(batch, cam);
		modInfo1.render(batch, cam);
		modInfo2.render(batch, cam);
		slider.render(batch, cam);

		batch.end();
	}

	@Override
	public void resize(int p1, int p2) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
	public class ModInfo extends InterfaceElement {
		private int WIDTH = Gdx.graphics.getWidth();
		private Texture background;
		private Checkbox modActiv;
		private Texture modIcon;
		private String modName = "";
		private BitmapFont text = new BitmapFont(Gdx.files.internal("ttf.fnt"));

		private int ModIconHeight;

		public ModInfo(int X, int Y) {
			setPosition(X, Y);
			setSize(WIDTH / 16 * 12, WIDTH / 16 * 2);
			ModIconHeight = height - height / 8 * 2;
			background = Textures.generateTexture(13, 2, true);
			modActiv = new Checkbox(t.switch_0, t.switch_1);
			modActiv.setSize(WIDTH / 16, WIDTH / 16);
			modActiv.setPosition(X + width - modActiv.width - WIDTH / 32, Y + (height - modActiv.height) / 2);
		}
		public void setMod(Mod mod) {
			this.modIcon = mod.modIcon;
			this.modName = mod.modName;
		}
		@Override
		public void update(Camera2D cam) {
			if (!isHided()) {
				super.update(cam);
				modActiv.update(cam);
			}
		}

		@Override
		public void render(SpriteBatch batch, Camera2D cam) {
			if (!isHided()) {
				super.render(batch, cam);
				batch.draw(background, cam.X + X, cam.Y + Y, width, height);
				batch.draw((modIcon == null) ? t.SAUWIcon : modIcon, cam.X + X + height / 8, cam.Y + Y + height / 8, ModIconHeight, ModIconHeight);
				text.draw(batch, modName, cam.X + X + ModIconHeight + height / 4, cam.Y + Y + height - height / 8);
				modActiv.render(batch, cam);
			}
		}
	}
}
