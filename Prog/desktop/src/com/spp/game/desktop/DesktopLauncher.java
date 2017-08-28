package com.spp.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spp.game.sppGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = sppGame.WIDTH;
		config.height = sppGame.HEIGHT;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		new LwjglApplication(new sppGame(), config);
	}
}
