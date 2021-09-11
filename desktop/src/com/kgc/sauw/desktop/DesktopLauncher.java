package com.kgc.sauw.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kgc.sauw.game.MainGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 840;
        config.height = 480;
        config.title = "S.A.U.W.";
        config.addIcon("icon/icon_16.png", Files.FileType.Internal);
        config.addIcon("icon/icon_32.png", Files.FileType.Internal);
        config.addIcon("icon/icon_64.png", Files.FileType.Internal);
        config.addIcon("icon/icon_128.png", Files.FileType.Internal);
        final MainGame game = new MainGame();
        new LwjglApplication(game, config);
    }
}
