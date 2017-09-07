package com.foureyed.un.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	/*
	 * En entity är ett objekt i spelet exempelvis tile, spelare, fiender
	 * I princip allt som kommer vara på banan
	 * de har en render och update för att ritas och hantera eventuell logik
	 * de har en konstruktor för att kallas i början
	 * de har en destroy som kallas sist
	 * de har en layer som hanterar vilket lager det ritas ut på
	 * och en EntityID id som håller kåll på vilket entity det är
	 * */
	
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
