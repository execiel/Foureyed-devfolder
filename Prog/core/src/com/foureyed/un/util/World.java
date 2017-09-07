package com.foureyed.un.util;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.foureyed.un.feGame;
import com.foureyed.un.entities.Entity;
import com.foureyed.un.entities.EntityID;

public class World {
	
	/*
	 * World är en bana i spelet i princip
	 * den har en map som är kartan
	 * den har en lista av entities som den renderar och updaterar
	 * den har en gravitation
	 * */
	
	public ArrayList<Entity> entities;
	public boolean removeEntities = false;
	public float gravity;
	private Camera cam;
	private Map map;
	
	public World(float gravity, Camera cam) {
		this.gravity = gravity;
		this.cam = cam;
		
		entities = new ArrayList<Entity>();
		map = new Map("map0.png", this);
		cam.position.y = -map.getHeight();
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		for(int i = 0; i <= 4; i++) {
			for(Entity e : entities) {
				if(i == e.layer && e.active) e.render(sb);
			}
		}
		sb.end();
		
	}
	
	public void update() {
		for(Entity e : entities) {
			if(e.active) e.update();
			if(e.id == EntityID.PLAYER) {
				//sätt kamerans position till spelarens
				Vector3 pos = new  Vector3(e.pos.x, e.pos.y, cam.position.z);
				cam.position.lerp(pos, 0.1f);
			}
		}
		
		//förhindrar kameran att åka utanför kartan
		if(cam.position.y >= -feGame.HEIGHT/2) cam.position.y = -feGame.HEIGHT/2;
		if(cam.position.y < -map.getHeight()+feGame.HEIGHT/2+32) cam.position.y = -map.getHeight()+feGame.HEIGHT/2+32;
		if(cam.position.x < feGame.WIDTH/2) cam.position.x = feGame.WIDTH/2;
		if(cam.position.x > map.getWidth()-feGame.WIDTH/2) cam.position.x = map.getWidth()-feGame.WIDTH/2;
		cam.update();

		if(removeEntities) entities.clear();
	}
	
	public void destroy() {
		for(Entity e : entities) {
			e.active = false;
			removeEntities = true;
		}
	}
}
