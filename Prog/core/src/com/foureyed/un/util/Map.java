package com.foureyed.un.util;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.foureyed.un.entities.EntityID;
import com.foureyed.un.entities.Player;
import com.foureyed.un.entities.Tile;

import box2dLight.PointLight;

public class Map {
	/*
	 * Map har en pixmap, som används för att kunna få färgen från en specifik pixel
	 * allt den gär är att loop alla rader på x för alla rader på y och på så sätt kollar efter färgen på varje pixel
	 * beroende på färgen lägger den till olika entities i banans entity lista
	 * getwidth och getheight används för att få bredden på mapen (i tiles dvs 32ggr större)
	 * */
	
	
	private Pixmap pm;
	private Random random = new Random();
	
	public Map(String path, Level world, World b2world) {
		pm = new Pixmap(Gdx.files.internal(path));
		System.out.println(pm.getHeight() + " " + pm.getWidth());
		
		
		for(int y = 0; y < pm.getHeight(); y++) {
			for(int x = 0; x < pm.getWidth(); x++) {
				int pix = pm.getPixel(x, y);
				//Gå igenom varje pixel och kolla dens färg, bestäm vilken entity som ska läggas till för vilken färg
				
				
				switch (pix) {
				case 0xff0000ff:
					world.entities.add(new Player(new Vector2(x*32, -y*32), world));
					break;
				case 0x000000ff:
					if(random.nextInt(10) == 3)
						world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[1][0], EntityID.TILE, 1, b2world));
					else
						world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[1][2], EntityID.TILE, 1, b2world));
					break;
				case 0x333333ff:
					world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[0][2], EntityID.TILE, 1, b2world));
					break;
				case 0x666666ff:
					world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[0][1], EntityID.TILE, 1, b2world));
					if(random.nextInt(3) == 1)
						world.entities.add(new Tile(new Vector2(x*32, -y*32+32), Assets.tile[0][0], EntityID.BGTILE, 2, b2world));
					break;
				case 0x999999ff:
					if(random.nextInt(3) == 2)
						world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[1][1], EntityID.TILE, 1, b2world));
					else 
						world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[2][0], EntityID.TILE, 1, b2world));
					break;
				case 0xccccccff:
					world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[2][2], EntityID.BGTILE, 0, b2world));
					break;
				case 0xffff00ff:
					world.entities.add(new Tile(new Vector2(x*32, -y*32), Assets.tile[2][1], EntityID.BGTILE, 1, b2world));
					new PointLight(world.rayhandler, 100, new Color(0.3f,0.2f,0,1), 250, x*32+16, -y*32+16);
					break;
				}
				
				
			}
		}
	}
	
	public int getWidth() { return pm.getWidth()*32; }
	public int getHeight() { return pm.getHeight()*32; }
	
}
