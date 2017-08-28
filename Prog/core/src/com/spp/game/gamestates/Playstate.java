package com.spp.game.gamestates;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spp.game.entities.Entity;
import com.spp.game.entities.Player;

public class Playstate extends Gamestate{

	
	private Camera cam;
	private World world;
	private Box2DDebugRenderer debugRend;
	
	private ArrayList<Entity> entities;
	
	BodyDef bdef;
	Body body;
	FixtureDef fdef;
	PolygonShape shape;
	
	@Override
	public void init() {
		cam = new OrthographicCamera(1280, 720);
		world = new World(new Vector2(0, -10f), true);
		debugRend = new Box2DDebugRenderer();
		entities = new ArrayList<Entity>();
		
		entities.add(new Player(world));
		
		bdef = new BodyDef();
		bdef.position.set(10, 10);
		bdef.type = BodyType.StaticBody;
		
		body = world.createBody(bdef);
		
		shape = new PolygonShape();
		shape.setAsBox(200, 30); //400 * 60 (???)
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		
		body.createFixture(fdef);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		
		
		batch.begin();
		for(Entity e : entities) {
			e.update();
		}
		
		batch.end();
		debugRend.render(world, cam.combined);
		
	}

	@Override
	public void update() {
		world.step(1/60, 6, 2);
		for(Entity e : entities) {
			e.update();
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public String getName() {
		return "playstate";
	}

}
