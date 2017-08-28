package com.spp.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity {
	
	BodyDef bdef;
	Body body;
	FixtureDef fdef;
	PolygonShape shape;

	
	public Entity(BodyType type, World world, Vector2 pos, Vector2 size) {
		bdef = new BodyDef();
		bdef.position.set(pos);
		bdef.type = type;
		
		body = world.createBody(bdef);
		
		shape = new PolygonShape();
		shape.setAsBox(size.x / 2, size.y / 2);
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		
		body.createFixture(fdef);
	}
	
	public abstract void render(SpriteBatch b);
	public abstract void update();
}
