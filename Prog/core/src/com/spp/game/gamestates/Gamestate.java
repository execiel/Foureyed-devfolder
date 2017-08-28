package com.spp.game.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Gamestate {
	public abstract String getName();
	public abstract void init();
	public abstract void render(SpriteBatch b); 
	public abstract void update(); 
	public abstract void destroy();
}
