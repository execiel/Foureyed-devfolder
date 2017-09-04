package com.foureyed.un.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	public Vector2 pos;
	public Vector2 size;
	public EntityID id;
	public boolean active;
	public int layer; /* 0-4 */
	
	public Entity(Vector2 pos, Vector2 size, EntityID id, int layer) {
		this.pos = pos;
		this.size = size;
		this.id = id;
		this.active = true;
		this.layer = layer;
	}
	
	public abstract void render(SpriteBatch sb);
	public abstract void update();
	public abstract void destroy();
}
