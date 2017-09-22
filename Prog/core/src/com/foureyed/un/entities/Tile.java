package com.foureyed.un.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Tile extends Entity{
	
	private Sprite sprite;
	private World world;
	
	public Tile(Vector2 pos, Sprite sprite, EntityID id, int layer, World world) {
		super(pos, new Vector2(32, 32), id, layer);
		this.sprite = sprite;
		this.world = world;
		if(id == EntityID.TILE) createBody();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(sprite, pos.x, pos.y, size.x, size.y);
		sb.setColor(new Color(1f, 1f, 1f, 1f));
	}

	@Override
	public void update() {

	}
	
	private void createBody() {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(pos.x+16, pos.y+16);
		Body b = world.createBody(bd);
		PolygonShape box = new PolygonShape();
		box.setAsBox(17, 17);
		FixtureDef def = new FixtureDef();
		def.shape = box;
		b.createFixture(def);
	}
	
	@Override
	public void destroy() {
		active = false;
	}

}
