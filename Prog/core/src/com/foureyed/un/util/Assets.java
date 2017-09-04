package com.foureyed.un.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.foureyed.un.gamestates.Gamestate;

public class Assets {

	public static Texture spritesheet, map;
	public static Sprite playerspr, tilespr;
	
	
	public static void load(Gamestate state) {
		if(state.getName() == "playstate") {
			spritesheet = new Texture("sheet.png");
			playerspr = new Sprite(spritesheet, 0, 0, 16, 16);
			tilespr = new Sprite(spritesheet, 16, 0, 16, 16);
			map = new Texture("map0.png");
		}
	}
	
	public static void dispose(Gamestate state) {
		if(state.getName() == "playstate") {
			spritesheet.dispose();
			map.dispose();
		}
	}
	
}
