package com.kgc.sauw.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kgc.sauw.game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1058;
		config.title = "S.A.U.W.";
		config.y = -1080;
		config.addIcon("icon.png", Files.FileType.Internal);
		final MainGame game = new MainGame();
		new LwjglApplication(game, config);
	}
}
