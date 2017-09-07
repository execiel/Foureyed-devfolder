package com.foureyed.un.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.foureyed.un.entities.Player;
import com.foureyed.un.entities.Tile;

public class Map {
	/*
	 * Map har en pixmap, som används för att kunna få färgen från en specifik pixel
	 * allt den gär är att loop alla rader på x för alla rader på y och på så sätt kollar efter färgen på varje pixel
	 * beroende på färgen lägger den till olika entities i banans entity lista
	 * getwidth och getheight används för att få bredden på mapen (i tiles dvs 32ggr större)
	 * */
	
	
	Pixmap pm;
	
	public Map(String path, World world) {
		pm = new Pixmap(Gdx.files.internal(path));
		System.out.println(pm.getHeight() + " " + pm.getWidth());
		
		for(int y = 0; y < pm.getHeight(); y++) {
			for(int x = 0; x < pm.getWidth(); x++) {
				int pix = pm.getPixel(x, y);
				if(pix == -16776961) world.entities.add(new Player(new Vector2(x*32, -y*32), world));
				if(pix == 255) world.entities.add(new Tile(new Vector2(x*32, -y*32)));
				
			}
		}
	}
	
	public int getWidth() { return pm.getWidth()*32; }
	public int getHeight() { return pm.getHeight()*32; }
	
}
