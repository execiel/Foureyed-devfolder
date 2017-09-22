package com.foureyed.un.util;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.foureyed.un.feGame;
import com.foureyed.un.entities.Entity;
import com.foureyed.un.entities.EntityID;

import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Level {
	
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
	private int level;
	
	public RayHandler rayhandler;
	private World world;
	private PointLight pl;
	
	public Level(float gravity, Camera cam, int level) {
		this.gravity = gravity;
		this.cam = cam;
		this.level = level;
		entities = new ArrayList<Entity>();
		world = new World(new Vector2(0, 0), false);
		rayhandler = new RayHandler(world);
		rayhandler.setAmbientLight(new Color(0.2f, 0, 0.2f, 0.4f));
		rayhandler.setBlurNum(4);
		map = new Map("map"+this.level+".png", this, world);

		
		pl = new PointLight(rayhandler, 100, new Color(0,0,0,1), 250, cam.position.x, cam.position.y);
		new DirectionalLight(rayhandler, 100, new Color(0.15f, 0.15f, 0, 1), -90);
		
		
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
				pl.setPosition(pos.x + e.size.x/2, pos.y + e.size.y/2);
				pl.update();
			}
		}
		
		//förhindrar kameran att åka utanför kartan
		if(cam.position.y >= -feGame.HEIGHT/2) cam.position.y = -feGame.HEIGHT/2;
		if(cam.position.y < -map.getHeight()+feGame.HEIGHT/2+32) cam.position.y = -map.getHeight()+feGame.HEIGHT/2+32;
		if(cam.position.x < feGame.WIDTH/2) cam.position.x = feGame.WIDTH/2;
		if(cam.position.x > map.getWidth()-feGame.WIDTH/2) cam.position.x = map.getWidth()-feGame.WIDTH/2;
		cam.update();
		rayhandler.setCombinedMatrix((OrthographicCamera) cam);
		rayhandler.updateAndRender();

		if(removeEntities) entities.clear();
	}
	
	public void destroy() {
		for(Entity e : entities) {
			e.active = false;
			removeEntities = true;
		}
	}
}
