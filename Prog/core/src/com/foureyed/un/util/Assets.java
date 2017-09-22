package com.foureyed.un.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.foureyed.un.gamestates.Gamestate;

public class Assets {
	
	//Laddar in alla texturer, ljud, filer etc. laddar in olika beroende på vilket gamestate kallar load
	
	public static Texture tiles, player;
	public static Sprite playerspr, tile[][];
	
	public static void load(Gamestate state) {
		if(state.getName() == "playstate") {
			tiles = new Texture("tiles.png");
			player = new Texture("sheet.png");
			playerspr = new Sprite(player, 0, 0, 16, 16);
			
			//Laddar in alla tiles i en dubbel lista
			int width = tiles.getWidth()/32;
			int height = tiles.getHeight()/32;
			tile = new Sprite[width][height];
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					tile[x][y] = new Sprite(tiles, x*32, y*32, 32, 32);
				}				
			}
			
			
		}
	}
	
	public static void dispose(Gamestate state) {
		if(state.getName() == "playstate") {
			tiles.dispose();
			player.dispose();
		}
	}
	
}
